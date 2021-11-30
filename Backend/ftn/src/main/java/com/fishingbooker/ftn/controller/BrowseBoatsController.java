package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.conversion.DataConverter;
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
    private final DataConverter converter;
    @GetMapping
    public List<BoatDto> findAll() {
        List<Boat> boats=boatService.findAll();
        List<BoatDto> boatsDtos=converter.convert(boats,BoatDto.class);
        return boatsDtos;
    }

    @GetMapping("{id}")
    public BoatDto findById(@PathVariable Long id) {
        return boatService.findById(id);
    }

}
