package com.fishingbooker.ftn.controller;


import com.fishingbooker.ftn.dto.AdventureCreationDto;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/adventures")
public class AdventureController {

    private final AdventureService adventureService;
    @PostMapping("add-adventure")
    public Long addAdventure(@RequestBody  AdventureCreationDto adventureDto){
        return adventureService.create(adventureDto);
    }
}
