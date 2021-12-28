package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.RequestApproval;
import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.repository.ReviewRepository;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import com.fishingbooker.ftn.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RegisteredClientService clientService;
    private final ReservationService reservationService;


    @Override
    public List<Reservation> getAvailableClientReviews(Long clientId) {
        List<Reservation> reservations = new ArrayList<>();

        for (Reservation reservation : reservationService.findAllByClient(clientId)) {
            if (reservation.getReservationEnd().isBefore(LocalDate.now())) {
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


}
