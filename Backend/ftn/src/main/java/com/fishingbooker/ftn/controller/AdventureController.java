package com.fishingbooker.ftn.controller;


import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureCreationDto;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/adventures")
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
}
