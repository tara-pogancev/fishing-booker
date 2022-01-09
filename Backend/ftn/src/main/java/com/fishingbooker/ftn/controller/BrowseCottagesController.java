package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import com.fishingbooker.ftn.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/cottages")
public class BrowseCottagesController {

    private final DataConverter converter;
    private final ReviewService reviewService;
    private final CottageService cottageService;

    @GetMapping
    public List<CottageDto> findAll() {
        List<Cottage> cottages = cottageService.findAll();
        List<CottageDto> cottageDtos = converter.convert(cottages, CottageDto.class);
        return cottageDtos;
    }

    @GetMapping("{id}")
    public CottageDto findById(@PathVariable Long id) {
        return cottageService.findById(id);
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewDto> getClientReviewsComplaints(@PathVariable Long id) {
        List<Review> reviews = reviewService.getEntityReviews("cottage", id);
        return converter.convert(reviews, ReviewDto.class);
    }

    @GetMapping("/reservations/{cottageId}")
    public List<ViewReservationDto> getBoatReservations(@PathVariable Long cottageId) {
        List<CottageReservation> reservations = cottageService.getReservationsByCottage(cottageId);
        return converter.convert(reservations, ViewReservationDto.class);
    }

}
