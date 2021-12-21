package com.fishingbooker.ftn.controller;


import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/adventures")
@PreAuthorize("hasRole('FISHING_INSTRUCTOR')")
public class AdventureController {

    private final AdventureService adventureService;
    private final DataConverter converter;

    @PostMapping("add-adventure")
    public Long addAdventure(@RequestBody AdventureCreationDto adventureDto) {
        return adventureService.create(adventureDto);
    }

    @GetMapping("get-instructor-adventures/{id}")
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

    // Post because of request body
    @PostMapping("/search")
    public List<AdventureDto> getSearch(@RequestBody EntitySearchDto filterDto) {
        List<Adventure> adventures = adventureService.findFiltered(filterDto);
        return converter.convert(adventures, AdventureDto.class);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAdventure(@PathVariable("id") Long id) {
        return adventureService.deleteAdventure(id);
    }
}
