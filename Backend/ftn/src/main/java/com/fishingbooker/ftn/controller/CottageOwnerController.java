package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.dto.CottageOwnerDto;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.service.interfaces.CottageOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cottage-owners")
public class CottageOwnerController {

    private final CottageOwnerService cottageOwnerService;
    private final DataConverter converter;

    @GetMapping()
    public List<ApplicationUserDto> getRegisteredOwners() {
        List<CottageOwner> cottageOwners = cottageOwnerService.getRegisteredOwners();
        return converter.convert(cottageOwners, ApplicationUserDto.class);
    }

    @GetMapping("/{id}")
    public CottageOwnerDto findById(@PathVariable String id) {
        Long idNum = Long.parseLong(id);
        CottageOwner cottageOwner = cottageOwnerService.findById(idNum);
        return converter.convert(cottageOwner, CottageOwnerDto.class);
    }

    @PutMapping
    public void update(@RequestBody CottageOwnerDto dto) {
        cottageOwnerService.update(dto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return cottageOwnerService.delete(id);
    }

    @GetMapping("/{id}/past-reservations")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<ReservationDto> getPastCottageReservations(@PathVariable Long id) {
        List<CottageReservation> reservations = cottageOwnerService.getPastCottageReservations(id);
        return converter.convert(reservations, ReservationDto.class);
    }


}
