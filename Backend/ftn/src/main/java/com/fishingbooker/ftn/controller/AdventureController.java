package com.fishingbooker.ftn.controller;


import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureCreationDto;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.EditAdventureDto;
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
    public Long addAdventure(@RequestBody  AdventureCreationDto adventureDto){
        return adventureService.create(adventureDto);
    }

    @GetMapping("get-instructor-adventures/{id}")
    public List<AdventureDto> getInstructorAdventures(@PathVariable("id") Long id){
        List<Adventure> adventures=adventureService.getInstructorAdventures(id);
        List<AdventureDto> adventureDtos=converter.convert(adventures,AdventureDto.class);
        return adventureDtos;
    }

    @GetMapping("/{id}")
    public EditAdventureDto getAdventure(@PathVariable("id") Long id){
        Adventure adventure=adventureService.get(id);
        EditAdventureDto dto=converter.convert(adventure, EditAdventureDto.class);
        return dto;
    }

    @DeleteMapping("/{id}")
    public boolean deleteAdventure(@PathVariable("id") Long id){
        return adventureService.deleteAdventure(id);
    }
}
