package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.dto.ReviewDto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ReviewService {

    List<Reservation> getAvailableClientReviews(Long clientId);

    List<Review> getReviewsByClient(Long clientId);

    void create(ReviewDto dto);

    List<Review> getEntityReviews(String type, Long id);

    List<Review> getApprovedReviews();

    void acceptReview(Long id);

    void declineReview(Long id);

    List<Review> findAll();
}
