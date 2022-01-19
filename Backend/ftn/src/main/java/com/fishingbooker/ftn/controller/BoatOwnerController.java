package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.BoatOwnerDto;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.service.interfaces.BoatOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boat-owners")
public class BoatOwnerController {

    private final DataConverter converter;
    private final BoatOwnerService boatOwnerService;

    @GetMapping("")
    public List<ApplicationUserDto> getEnabledOwners() {
        List<BoatOwner> boatOwners = boatOwnerService.getRegisteredBoatOwners();
        return converter.convert(boatOwners, ApplicationUserDto.class);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return boatOwnerService.delete(id);
    }

    @GetMapping("/{id}")
    public BoatOwnerDto findById(@PathVariable String id) {
        Long idNum = Long.parseLong(id);
        BoatOwner boatOwner = boatOwnerService.findById(idNum);
        return converter.convert(boatOwner, BoatOwnerDto.class);
    }

    @PutMapping
    public void update(@RequestBody BoatOwnerDto dto) {
        boatOwnerService.update(dto);
    }

    @GetMapping("/{id}/past-reservations")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public List<ReservationDto> getPastBoatReservations(@PathVariable Long id) {
        List<BoatReservation> reservations = boatOwnerService.getPastBoatReservations(id);
        return converter.convert(reservations, ReservationDto.class);
    }
}
