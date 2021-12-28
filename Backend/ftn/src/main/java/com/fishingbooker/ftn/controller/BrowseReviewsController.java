package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/reviews")
public class BrowseReviewsController {

    private final DataConverter converter;
    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewService.getApprovedReviews();
        return converter.convert(reviews, ReviewDto.class);
    }

}
