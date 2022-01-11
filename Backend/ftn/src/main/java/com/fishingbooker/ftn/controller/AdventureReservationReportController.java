package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.reservation_report.AdventureReservationReport;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.service.interfaces.AdventureReservationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adventure-reservation-report")
@RequiredArgsConstructor
public class AdventureReservationReportController {

    private final AdventureReservationReportService adventureReservationReportService;
    private final DataConverter converter;

    @GetMapping("/unprocessed")
    List<ReservationReportDto> getUnprocessed(){
        List<AdventureReservationReport> reservationReports=adventureReservationReportService.getUnprocessedReports();
        List<ReservationReportDto> reservationReportDtos=converter.convert(reservationReports, ReservationReportDto.class);
        return reservationReportDtos;
    }

    @PostMapping("/add-adventure-reservation-report")
    public Long addReport(@RequestBody CreateAdventureReservationReportDto adventureReportDto)
    {
        return adventureReservationReportService.createAdventureReservationReport(adventureReportDto);
    }

    @PutMapping("/give-penalty")
    public Long givePenalty(@RequestBody ReservationReportDto reservationReportDto){
        return adventureReservationReportService.givePenaltyToClient(reservationReportDto);
    }

    @PutMapping("/forgive-client")
    public Long forgiveClient(@RequestBody ReservationReportDto reservationReportDto){
        return adventureReservationReportService.forgiveClient(reservationReportDto);

    }
}
