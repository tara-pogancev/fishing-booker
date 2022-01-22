package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import com.fishingbooker.ftn.service.interfaces.AdventureReservationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adventure-reservation-report")
@RequiredArgsConstructor
public class AdventureReservationReportController {

    private final AdventureReservationReportService adventureReservationReportService;
    private final DataConverter converter;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/unprocessed")
    List<ReservationReportDto> getUnprocessed() {
        List<AdventureReservationReport> reservationReports = adventureReservationReportService.getUnprocessedReports();
        List<ReservationReportDto> reservationReportDtos = converter.convert(reservationReports, ReservationReportDto.class);
        return reservationReportDtos;
    }

    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/add-adventure-reservation-report")
    public Long addReport(@RequestBody CreateAdventureReservationReportDto adventureReportDto) {
        return adventureReservationReportService.createAdventureReservationReport(adventureReportDto);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/give-penalty")
    public Long givePenalty(@RequestBody ReservationReportDto reservationReportDto) {
        return adventureReservationReportService.givePenaltyToClient(reservationReportDto);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/forgive-client")
    public Long forgiveClient(@RequestBody ReservationReportDto reservationReportDto) {
        return adventureReservationReportService.forgiveClient(reservationReportDto);

    }
}
