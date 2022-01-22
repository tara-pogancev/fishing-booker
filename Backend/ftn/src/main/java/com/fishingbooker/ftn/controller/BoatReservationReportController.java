package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import com.fishingbooker.ftn.service.interfaces.BoatReservationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boat-reservation-report")
@RequiredArgsConstructor
public class BoatReservationReportController {
    private final BoatReservationReportService boatReservationReportService;
    private final DataConverter converter;

    @PostMapping("/add-boat-reservation-report")
    public Long addReport(@RequestBody CreateAdventureReservationReportDto boatReportDto) {
        return boatReservationReportService.createBoatReservationReport(boatReportDto);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/unprocessed")
    List<ReservationReportDto> getUnprocessed() {
        List<BoatReservationReport> reservationReports = boatReservationReportService.getUnprocessedReports();
        List<ReservationReportDto> reservationReportDtos = converter.convert(reservationReports, ReservationReportDto.class);
        return reservationReportDtos;
    }
}
