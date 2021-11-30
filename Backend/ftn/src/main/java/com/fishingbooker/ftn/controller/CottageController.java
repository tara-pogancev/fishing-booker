package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cottages")
public class CottageController {

    private final DataConverter converter;
    private final CottageService cottageService;

    @GetMapping()
    public List<CottageDto> get(){
        List<Cottage> cottages=cottageService.findAll();
        return converter.convert(cottages,CottageDto.class);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id){
        return cottageService.delete(id);
    }


}
