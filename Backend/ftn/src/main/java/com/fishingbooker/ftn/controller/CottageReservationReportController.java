package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.service.interfaces.CottageReservationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cottage-reservation-report")
@RequiredArgsConstructor
public class CottageReservationReportController {
    private final CottageReservationReportService cottageReservationReportService;
    
    @PostMapping("/add-cottage-reservation-report")
    public Long addReport(@RequestBody CreateAdventureReservationReportDto cottageReportDto) {
        return cottageReservationReportService.createCottageReservationReport(cottageReportDto);
    }
}
