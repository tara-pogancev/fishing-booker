package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.boats.*;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.service.interfaces.BoatService;
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
@RequestMapping("api/boats")
public class BoatController {

    private final BoatService boatService;
    private final DataConverter converter;
    private final ReservationService reservationService;
    private final RegisteredClientService clientService;

    @GetMapping()
    public List<BoatDto> get() {
        List<Boat> boats = boatService.findAll();
        return converter.convert(boats, BoatDto.class);
    }

    // Post because of request body
    @PostMapping("/search/{userId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public ResponseEntity<List<BoatDto>> getSearch(@RequestBody EntitySearchDto filterDto, @PathVariable Long userId) {
        if (clientService.clientHasOverlappingReservation(filterDto.startDate, filterDto.endDate, userId)) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            List<Boat> boats = boatService.findFiltered(filterDto, userId);
            List<BoatDto> dtoRetVal = converter.convert(boats, BoatDto.class);
            return new ResponseEntity<>(dtoRetVal, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public Boolean delete(@PathVariable("id") Long id) {
        return boatService.delete(id);
    }

    @PostMapping("/book")
    public ResponseEntity<Long> book(@RequestBody ReservationDto reservationDto) {
        BoatReservation reservation = reservationService.bookBoat(reservationDto);
        if (reservation == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(reservation.getId(), HttpStatus.OK);
        }
    }

    @GetMapping("/findByBoatOwnerId/{id}")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public List<BoatDto> getByBoatOwnerId(@PathVariable("id") long id) {
        return boatService.findByBoatOwnerId(id);
    }

    @PostMapping("add-boat")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public Long addBoat(@RequestBody BoatCreationDto boatDto) {
        return boatService.create(boatDto);
    }

    @GetMapping("/getBoatTypes/")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public List<BoatType> getBoatTypes() {
        return boatService.getBoatTypes();
    }

    @GetMapping("/getNavigationalEquipment/")
    @PreAuthorize("hasRole('BOAT_OWNER')")
    public List<NavigationalEquipment> getNavigationalEquipment() {
        return boatService.getNavigationalEquipment();
    }

    @GetMapping("/{id}")
    public BoatCreationDto getBoat(@PathVariable("id") Long id) {
        Boat boat = boatService.get(id);
        return converter.convert(boat, BoatCreationDto.class);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @PostMapping("/add-quick-reservation")
    public Long addQuickReservation(@RequestBody AdventureQuickReservationDto dto) {
        return boatService.createQuickReservation(dto);
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @PostMapping("/add-reservation")
    public Long addReservation(@RequestBody NewReservationDto dto) {
        Long ret = boatService.createReservation(dto);
        return ret;
    }

    @PreAuthorize("hasRole('BOAT_OWNER')")
    @GetMapping("/get-boat-utilities/{id}")
    public List<BoatUtilityDto> getBoatUtilities(@PathVariable Long id) {
        List<BoatUtility> utilities = boatService.getBoatUtilities(id);
        List<BoatUtilityDto> utilityDtos = converter.convert(utilities, BoatUtilityDto.class);
        return utilityDtos;
    }

}
