package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.AdventureDto;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/adventures")
public class BrowseAdventuresController {

    private final AdventureService adventureService;

    @GetMapping
    public List<AdventureDto> findAll() {
        return adventureService.findAll();
    }

    @GetMapping("{id}")
    public AdventureDto findById(@PathVariable Long id) {
        return adventureService.findById(id);
    }

}
