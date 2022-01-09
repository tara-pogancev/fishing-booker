package com.fishingbooker.ftn.controller;


import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
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
@RequestMapping("api/adventures")
public class AdventureController {

    private final DataConverter converter;
    private final AdventureService adventureService;
    private final ReservationService reservationService;
    private final RegisteredClientService clientService;

    @PostMapping("add-adventure")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public Long addAdventure(@RequestBody AdventureCreationDto adventureDto) {
        return adventureService.create(adventureDto);
    }

    @GetMapping("get-instructor-adventures/{id}")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public List<AdventureDto> getInstructorAdventures(@PathVariable("id") Long id) {
        List<Adventure> adventures = adventureService.getInstructorAdventures(id);
        List<AdventureDto> adventureDtos = converter.convert(adventures, AdventureDto.class);
        return adventureDtos;
    }

    @GetMapping("/{id}")
    public EditAdventureDto getAdventure(@PathVariable("id") Long id) {
        Adventure adventure = adventureService.get(id);
        EditAdventureDto dto = converter.convert(adventure, EditAdventureDto.class);
        return dto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public boolean deleteAdventure(@PathVariable("id") Long id) {
        return adventureService.deleteAdventure(id);
    }

    @PostMapping("/book")
    public Long book(@RequestBody ReservationDto reservationDto) {
        return reservationService.bookAdventure(reservationDto).getId();
    }


    //@PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    @PostMapping("/add-quick-reservation")
    public Long addQuickReservation(@RequestBody AdventureQuickReservationDto dto) {
        AdventureQuickReservation reservation = converter.convert(dto, AdventureQuickReservation.class);
        return adventureService.createQuickReservation(reservation);
    }

    @PostMapping("/add-reservation")
    public Long addReservation(@RequestBody InstructorNewReservationDto dto) {
        return adventureService.createReservation(dto);
    }

    @GetMapping("/get-quick-reservations-calendar/{id}")
    public List<AdventureQuickReservationCalendarDto> getAdventureQuickReservation(@PathVariable Long id) {
        List<AdventureQuickReservation> reservations = adventureService.getQuickReservations(id);
        List<AdventureQuickReservationCalendarDto> reservationsDto = converter.convert(reservations, AdventureQuickReservationCalendarDto.class);
        return reservationsDto;
    }

    @GetMapping("/get-reservation/{id}")
    public ShowReservationInCalendarDto getReservationForCalendar(@PathVariable Long id) {
        AdventureReservation adventureReservation = reservationService.getAdventureReservation(id);
        ShowReservationInCalendarDto dto = converter.convert(adventureReservation, ShowReservationInCalendarDto.class);
        return dto;
    }

    @GetMapping("/get-quick-reservation/{id}")
    public ShowReservationInCalendarDto getQuickReservationForCalendar(@PathVariable Long id) {
        AdventureQuickReservation adventureReservation = reservationService.getAdventureQuickReservation(id);
        ShowReservationInCalendarDto dto = converter.convert(adventureReservation, ShowReservationInCalendarDto.class);
        return dto;
    }

    @GetMapping("/get-adventure-utilities/{id}")
    public List<AdventureUtilityDto> getAdventureUtilities(@PathVariable Long id) {
        List<AdventureUtility> utilities = adventureService.getAdventureUtilities(id);
        List<AdventureUtilityDto> utilityDtos = converter.convert(utilities, AdventureUtilityDto.class);
        return utilityDtos;
    }

    // Post because of request body
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    @PostMapping("/search/{userId}")
    public ResponseEntity<List<AdventureDto>> getSearch(@RequestBody EntitySearchDto filterDto, @PathVariable Long userId) {
        if (clientService.clientHasOverlappingReservation(filterDto.startDate, filterDto.endDate, userId)) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            List<Adventure> adventures = adventureService.findFiltered(filterDto);
            List<AdventureDto> dtoRetVal = converter.convert(adventures, AdventureDto.class);
            return new ResponseEntity<>(dtoRetVal, HttpStatus.OK);
        }
    }


}
