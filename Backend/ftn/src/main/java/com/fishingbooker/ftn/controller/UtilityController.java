package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import com.fishingbooker.ftn.service.interfaces.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/utilities")
public class UtilityController {

    private final UtilityService utilityService;
    @GetMapping()
    public List<Utility> get(){
        return utilityService.get();
    }
}
