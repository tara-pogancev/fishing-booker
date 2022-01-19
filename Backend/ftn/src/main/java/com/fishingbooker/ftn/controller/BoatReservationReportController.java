package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.service.interfaces.BoatReservationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boat-reservation-report")
@RequiredArgsConstructor
public class BoatReservationReportController {
    private final BoatReservationReportService boatReservationReportService;

    @PostMapping("/add-boat-reservation-report")
    public Long addReport(@RequestBody CreateAdventureReservationReportDto boatReportDto) {
        return boatReservationReportService.createBoatReservationReport(boatReportDto);
    }
}
