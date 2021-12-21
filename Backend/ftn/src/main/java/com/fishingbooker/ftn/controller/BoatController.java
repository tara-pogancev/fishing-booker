package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/boats")
public class BoatController {

    private final BoatService boatService;
    private final DataConverter converter;

    @GetMapping()
    public List<BoatDto> get() {
        List<Boat> boats = boatService.findAll();
        return converter.convert(boats, BoatDto.class);
    }

    // Post because of request body
    @PostMapping("/search")
    public List<BoatDto> getSearch(@RequestBody EntitySearchDto filterDto) {
        List<Boat> boats = boatService.findFiltered(filterDto);
        return converter.convert(boats, BoatDto.class);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return boatService.delete(id);
    }
}
