package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.ReviewDto;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import com.fishingbooker.ftn.service.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/boats")
public class BrowseBoatsController {

    private final BoatService boatService;
    private final DataConverter converter;
    private final ReviewService reviewService;

    @GetMapping
    public List<BoatDto> findAll() {
        List<Boat> boats = boatService.findAll();
        List<BoatDto> boatsDtos = converter.convert(boats, BoatDto.class);
        return boatsDtos;
    }

    @GetMapping("{id}")
    public ResponseEntity<BoatDto> findById(@PathVariable Long id) {
        BoatDto boatDto = boatService.findById(id);
        if (boatDto == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(boatDto, HttpStatus.OK);
        }
    }

    @GetMapping("/reviews/{id}")
    public List<ReviewDto> getClientReviewsComplaints(@PathVariable Long id) {
        List<Review> reviews = reviewService.getEntityReviews("boat", id);
        return converter.convert(reviews, ReviewDto.class);
    }

    @GetMapping("/reservations/{boatId}")
    public List<ViewReservationDto> getBoatReservations(@PathVariable Long boatId) {
        List<BoatReservation> reservations = boatService.getReservationsByBoat(boatId);
        return converter.convert(reservations, ViewReservationDto.class);
    }

}
