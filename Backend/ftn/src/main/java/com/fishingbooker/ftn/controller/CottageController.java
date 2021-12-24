package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.CottageCreationDto;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cottages")
public class CottageController {

    private final DataConverter converter;
    private final CottageService cottageService;
    private final ReservationService reservationService;

    @GetMapping()
    public List<CottageDto> get() {
        List<Cottage> cottages = cottageService.findAll();
        return converter.convert(cottages, CottageDto.class);
    }

    @GetMapping("/{id}")
    public CottageCreationDto getCottage(@PathVariable("id") Long id) {
        Cottage cottage = cottageService.get(id);
        return converter.convert(cottage, CottageCreationDto.class);
    }

    @GetMapping("/findByCottageOwnerId/{id}")
    public List<CottageDto> getByCottageOwnerId(@PathVariable("id") long id) {
        return cottageService.findByCottageOwnerId(id);
    }

    // Post because of request body
    @PostMapping("/search")
    public List<CottageDto> getSearch(@RequestBody EntitySearchDto filterDto) {
        List<Cottage> cottages = cottageService.findFiltered(filterDto);
        return converter.convert(cottages, CottageDto.class);
    }

    @PostMapping("add-cottage")
    public Long addCottage(@RequestBody CottageCreationDto cottageDto) {
        return cottageService.create(cottageDto);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return cottageService.delete(id);
    }

    @PostMapping("/book")
    public Long book(@RequestBody ReservationDto reservationDto) {
        return reservationService.bookCottage(reservationDto).getId();
    }


}
