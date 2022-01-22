package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.reservation_report.BoatReservationReport;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CreateAdventureReservationReportDto;
import com.fishingbooker.ftn.dto.ReservationReportDto;
import com.fishingbooker.ftn.service.interfaces.CottageReservationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cottage-reservation-report")
@RequiredArgsConstructor
public class CottageReservationReportController {
    private final CottageReservationReportService cottageReservationReportService;
    private final DataConverter converter;
    
    @PostMapping("/add-cottage-reservation-report")
    public Long addReport(@RequestBody CreateAdventureReservationReportDto cottageReportDto) {
        return cottageReservationReportService.createCottageReservationReport(cottageReportDto);
    }


}
