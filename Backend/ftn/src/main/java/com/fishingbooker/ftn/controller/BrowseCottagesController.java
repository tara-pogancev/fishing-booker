package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/cottages")
public class BrowseCottagesController {

    private final CottageService cottageService;

    @GetMapping
    public List<CottageDto> findAll() {
        return cottageService.findAll();
    }

    @GetMapping("{id}")
    public CottageDto findById(@PathVariable Long id) {
        return cottageService.findById(id);
    }

    @PostMapping
    public void initCottages() {
        cottageService.initCottages();
    }
}
