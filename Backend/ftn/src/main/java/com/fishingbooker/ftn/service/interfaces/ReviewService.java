package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.service.ReviewServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReviewService {

    List<Reservation> getAvailableClientReviews(Long clientId);

    List<Review> getReviewsByClient(Long clientId);

    void create(ReviewDto dto);
}
