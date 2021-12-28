package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import com.fishingbooker.ftn.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final DataConverter converter;
    private final ReviewService reviewService;

    @GetMapping("/available-reviews/{clientId}")
    public List<ViewReservationDto> getAvailableClientReviews(@PathVariable Long clientId) {
        List<Reservation> reservations = reviewService.getAvailableClientReviews(clientId);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @GetMapping("/client-reviews-complaints/{clientId}")
    public List<ReviewDto> getClientReviewsComplaints(@PathVariable Long clientId) {
        List<Review> reviews = reviewService.getReviewsByClient(clientId);
        return converter.convert(reviews, ReviewDto.class);
    }

    @PostMapping("/new-review")
    public void getAvailableClientReviews(@RequestBody ReviewDto dto) {
        reviewService.create(dto);
    }

}
