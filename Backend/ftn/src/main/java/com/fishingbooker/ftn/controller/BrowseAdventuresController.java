package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import com.fishingbooker.ftn.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/adventures")
public class BrowseAdventuresController {

    private final DataConverter converter;
    private final ReviewService reviewService;
    private final AdventureService adventureService;

    @GetMapping
    public List<AdventureDto> findAll() {
        return adventureService.findAll();
    }

    @GetMapping("{id}")
    public AdventureDto findById(@PathVariable Long id) {
        return adventureService.findById(id);
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewDto> getClientReviewsComplaints(@PathVariable Long id) {
        List<Review> reviews = reviewService.getEntityReviews("adventure", id);
        return converter.convert(reviews, ReviewDto.class);
    }


}
