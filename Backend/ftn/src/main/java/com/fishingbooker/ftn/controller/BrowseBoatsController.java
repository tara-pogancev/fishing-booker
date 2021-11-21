package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/browse/boats")
public class BrowseBoatsController {

    private final BoatService boatService;

    @GetMapping
    public List<BoatDto> findAll() {
        return boatService.findAll();
    }

    @GetMapping("{id}")
    public BoatDto findById(@PathVariable Long id) {
        return boatService.findById(id);
    }

}
