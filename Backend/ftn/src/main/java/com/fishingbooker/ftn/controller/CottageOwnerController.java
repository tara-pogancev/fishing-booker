package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.service.interfaces.CottageOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cottage-owners")
public class CottageOwnerController {

    private final CottageOwnerService cottageOwnerService;
    private final DataConverter converter;

    @GetMapping()
    public List<ApplicationUserDto> getRegisteredOwners(){
        List<CottageOwner> cottageOwners=cottageOwnerService.getRegisteredOwners();
        return converter.convert(cottageOwners,ApplicationUserDto.class);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id){
        return cottageOwnerService.delete(id);
    }


}
