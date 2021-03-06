package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.email.context.ReviewUpdateEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.AdventureRepository;
import com.fishingbooker.ftn.repository.BoatRepository;
import com.fishingbooker.ftn.repository.CottageRepository;
import com.fishingbooker.ftn.repository.ReviewRepository;
import com.fishingbooker.ftn.service.interfaces.ApplicationUserService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import com.fishingbooker.ftn.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final EmailService emailService;
    private final ReviewRepository reviewRepository;
    private final ReservationService reservationService;

    private final BoatRepository boatRepository;
    private final ApplicationUserService userService;
    private final CottageRepository cottageRepository;
    private final AdventureRepository adventureRepository;

    @Override
    public List<Reservation> getAvailableClientReviews(Long clientId) {
        List<Reservation> reservations = new ArrayList<>();

        for (Reservation reservation : reservationService.findAllByClient(clientId)) {
            if (reservation.getReservationEnd().isBefore(LocalDateTime.now())) {
                boolean hasReview = false;
                for (Review review : getReviewsByClient(clientId))
                    if (review.getReservation().getId() == reservation.getId()) {
                        hasReview = true;
                        break;
                    }

                if (!hasReview)
                    reservations.add(reservation);
            }
        }

        return reservations;
    }

    @Override
    public List<Review> getReviewsByClient(Long clientId) {
        List<Review> reviews = new ArrayList<>();
        for (Review review : reviewRepository.findAll()) {
            if (review.getClient().getId() == clientId)
                reviews.add(review);
        }
        return reviews;
    }

    @Override
    public void create(ReviewDto dto) {
        if (reservationService.find(dto.id) != null) {

            CottageReservation cottageReservation = reservationService.findCottageReservation(dto.id);
            BoatReservation boatReservation = reservationService.findBoatReservation(dto.id);
            AdventureReservation adventureReservation = reservationService.findAdventureReservation(dto.id);

            if (cottageReservation != null) {
                saveCottageReview(dto, cottageReservation);
            } else if (boatReservation != null) {
                saveBoatReview(dto, boatReservation);
            } else if (adventureReservation != null) {
                saveAdventureReview(dto, adventureReservation);
            }
        }
    }

    public void recalculateCottageRating(Long cottageId) {
        Cottage cottage = cottageRepository.get(cottageId);
        if (cottage != null) {
            double sum = 0;
            for (Review review : getEntityReviews("cottage", cottageId))
                sum += review.getRating();

            int numberOfReviews = getEntityReviews("cottage", cottageId).size();
            if (numberOfReviews == 0) {
                cottage.setRating(0.0);
            } else {
                cottage.setRating(sum / numberOfReviews);
            }

            cottageRepository.save(cottage);
        }
    }

    public void recalculateBoatRating(Long boatId) {
        Boat boat = boatRepository.get(boatId);
        if (boat != null) {
            double sum = 0;
            for (Review review : getEntityReviews("boat", boatId))
                sum += review.getRating();

            int numberOfReviews = getEntityReviews("boat", boatId).size();
            if (numberOfReviews == 0) {
                boat.setRating(0.0);
            } else {
                boat.setRating(sum / numberOfReviews);
            }

            boatRepository.save(boat);
        }
    }

    public void recalculateAdventureRating(Long adventureId) {
        Adventure adventure = adventureRepository.get(adventureId);
        if (adventure != null) {
            double sum = 0;
            for (Review review : getEntityReviews("adventure", adventureId))
                sum += review.getRating();

            int numberOfReviews = getEntityReviews("adventure", adventureId).size();
            if (numberOfReviews == 0) {
                adventure.setRating(0.0);
            } else {
                adventure.setRating(sum / numberOfReviews);
            }

            adventureRepository.save(adventure);
        }
    }


    @Override
    public List<Review> getEntityReviews(String type, Long id) {
        List<Review> reviews = new ArrayList<>();

        for (Review review : reviewRepository.findAll()) {
            if (review.getReservationType().equals(type)
                    && review.getEntityId() == id && review.getApproval() == RequestApproval.APPROVED)
                reviews.add(review);
        }

        return reviews;
    }

    @Override
    public List<Review> getApprovedReviews() {
        List<Review> reviews = new ArrayList<>();

        for (Review review : reviewRepository.findAll()) {
            if (review.getApproval() == RequestApproval.APPROVED)
                reviews.add(review);
        }

        return reviews;
    }

    @Override
    public void acceptReview(Long id) {
        Review review = reviewRepository.get(id);
        if (review != null) {
            review.setApproval(RequestApproval.APPROVED);
            reviewRepository.save(review);
            recalculateRating(review);
            sendReviewUpdateEmail(review);
        }
    }

    @Override
    public void declineReview(Long id) {
        Review review = reviewRepository.get(id);
        if (review != null) {
            review.setApproval(RequestApproval.DECLINED);
            reviewRepository.save(review);
            recalculateRating(review);
            sendReviewUpdateEmail(review);
        }
    }

    private void recalculateRating(Review review) {
        if (review.getReservationType().equals("cottage")) {
            recalculateCottageRating(review.getEntityId());
        } else if (review.getReservationType().equals("boat")) {
            recalculateBoatRating(review.getEntityId());
        } else if (review.getReservationType().equals("adventure")) {
            recalculateAdventureRating(review.getEntityId());
        }
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    private void saveAdventureReview(ReviewDto dto, AdventureReservation adventureReservation) {
        Review review = new Review();
        review.setReview(dto.review);
        review.setClient(adventureReservation.getReservationClient());
        review.setEntityId(adventureReservation.getAdventure().getId());
        review.setRating(dto.rating);
        review.setReservation(adventureReservation);
        review.setDate(LocalDateTime.now());
        review.setReservationType("adventure");
        reviewRepository.save(review);
    }

    private void saveBoatReview(ReviewDto dto, BoatReservation boatReservation) {
        Review review = new Review();
        review.setReview(dto.review);
        review.setEntityId(boatReservation.getBoat().getId());
        review.setClient(boatReservation.getReservationClient());
        review.setRating(dto.rating);
        review.setReservation(boatReservation);
        review.setDate(LocalDateTime.now());
        review.setReservationType("boat");
        reviewRepository.save(review);
    }

    private void saveCottageReview(ReviewDto dto, CottageReservation cottageReservation) {
        Review review = new Review();
        review.setReview(dto.review);
        review.setReservationType("cottage");
        review.setEntityId(cottageReservation.getCottage().getId());
        review.setClient(cottageReservation.getReservationClient());
        review.setRating(dto.rating);
        review.setReservation(cottageReservation);
        review.setDate(LocalDateTime.now());
        reviewRepository.save(review);
    }

    public void sendReviewUpdateEmail(Review review) {
        ApplicationUser user = userService.findById(review.getClient().getId());
        ReviewUpdateEmailContext emailContext = new ReviewUpdateEmailContext();
        emailContext.init(user);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
