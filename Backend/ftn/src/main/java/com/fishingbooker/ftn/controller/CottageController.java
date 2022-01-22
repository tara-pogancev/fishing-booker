package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cottages")
public class CottageController {

    private final DataConverter converter;
    private final CottageService cottageService;
    private final ReservationService reservationService;
    private final RegisteredClientService clientService;

    @GetMapping()
    public List<CottageDto> get() {
        List<Cottage> cottages = cottageService.findAll();
        return converter.convert(cottages, CottageDto.class);
    }

    @GetMapping("/{id}")
    public CottageCreationDto getCottage(@PathVariable("id") Long id) {
        Cottage cottage = cottageService.get(id);
        return converter.convert(cottage, CottageCreationDto.class);
    }

    @GetMapping("/findByCottageOwnerId/{id}")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public List<CottageDto> getByCottageOwnerId(@PathVariable("id") long id) {
        return cottageService.findByCottageOwnerId(id);
    }

    // Post because of request body
    @PostMapping("/search/{userId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public ResponseEntity<List<CottageDto>> getSearch(@RequestBody EntitySearchDto filterDto, @PathVariable Long userId) {
        if (clientService.clientHasOverlappingReservation(filterDto.startDate, filterDto.endDate, userId)) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            List<Cottage> cottages = cottageService.findFiltered(filterDto, userId);
            List<CottageDto> dtoRetVal = converter.convert(cottages, CottageDto.class);
            return new ResponseEntity<>(dtoRetVal, HttpStatus.OK);
        }
    }

    @PostMapping("add-cottage")
    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    public Long addCottage(@RequestBody CottageCreationDto cottageDto) {
        return cottageService.create(cottageDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('COTTAGE_OWNER')")
    public Boolean delete(@PathVariable("id") Long id) {
        return cottageService.delete(id);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping("admin-delete/{id}")
    public void deleteByAdmin(@PathVariable("id")Long id){
        cottageService.deleteByAdmin(id);
    }

    @PostMapping("/book")
    public ResponseEntity<Long> book(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.bookCottage(reservationDto);
        if (reservation == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(reservation.getId(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @PostMapping("/add-quick-reservation")
    public Long addQuickReservation(@RequestBody AdventureQuickReservationDto dto) {
        return cottageService.createQuickReservation(dto);
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @PostMapping("/add-reservation")
    public Long addReservation(@RequestBody NewReservationDto dto) {
        Long ret = cottageService.createReservation(dto);
        return ret;
    }

    @PreAuthorize("hasRole('COTTAGE_OWNER')")
    @GetMapping("/get-cottage-utilities/{id}")
    public List<CottageUtilityDto> getCottageUtilities(@PathVariable Long id) {
        List<CottageUtility> utilities = cottageService.getCottageUtilities(id);
        List<CottageUtilityDto> utilityDtos = converter.convert(utilities, CottageUtilityDto.class);
        return utilityDtos;
    }
}
