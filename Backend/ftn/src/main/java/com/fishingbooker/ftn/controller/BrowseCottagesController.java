package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/cottages")
public class BrowseCottagesController {

    private final CottageService cottageService;
    private final DataConverter converter;

    @GetMapping
    public List<CottageDto> findAll() {
        List<Cottage> cottages = cottageService.findAll();
        List<CottageDto> cottageDtos = converter.convert(cottages, CottageDto.class);
        return cottageDtos;
    }

    @GetMapping("{id}")
    public CottageDto findById(@PathVariable Long id) {
        return cottageService.findById(id);
    }

}
