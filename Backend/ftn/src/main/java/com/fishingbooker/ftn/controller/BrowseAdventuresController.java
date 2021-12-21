package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/adventures")
public class BrowseAdventuresController {

    private final AdventureService adventureService;
    private final DataConverter converter;

    @GetMapping
    public List<AdventureDto> findAll() {
        return adventureService.findAll();
    }

    @GetMapping("{id}")
    public AdventureDto findById(@PathVariable Long id) {
        return adventureService.findById(id);
    }

    // Post because of request body
    @PostMapping("/search")
    public List<AdventureDto> getSearch(@RequestBody EntitySearchDto filterDto) {
        List<Adventure> adventures = adventureService.findFiltered(filterDto);
        return converter.convert(adventures, AdventureDto.class);
    }

}
