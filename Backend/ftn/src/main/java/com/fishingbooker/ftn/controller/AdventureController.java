package com.fishingbooker.ftn.controller;


import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/adventures")
public class AdventureController {

    private final DataConverter converter;
    private final ReservationService reservationService;
    private final AdventureService adventureService;

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

    @PostMapping("add-quick-reservation")
    @PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
    public Long addQuickReservation(@RequestBody AdventureQuickReservationDto dto){
        AdventureQuickReservation reservation=converter.convert(dto,AdventureQuickReservation.class);
        return adventureService.createQuickReservation(reservation);
    }

    @GetMapping("get-quick-reservations-calendar/{id}")
    public List<AdventureQuickReservationCalendarDto> getAdventureQuickReservation(@PathVariable  Long id){
        List<AdventureQuickReservation> reservations=adventureService.getQuickReservations(id);
        List<AdventureQuickReservationCalendarDto> reservationsDto=converter.convert(reservations,AdventureQuickReservationCalendarDto.class);
        return reservationsDto;
    }

    @GetMapping("get-reservation/{id}")
    public ShowReservationInCalendarDto getReservationForCalendar(@PathVariable Long id){
        AdventureReservation adventureReservation=reservationService.getAdventureReservation(id);
        ShowReservationInCalendarDto dto=converter.convert(adventureReservation,ShowReservationInCalendarDto.class);
        return dto;
    }


}
