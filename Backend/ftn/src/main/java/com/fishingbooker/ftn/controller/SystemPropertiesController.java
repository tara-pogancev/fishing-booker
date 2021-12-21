package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.service.interfaces.SystemPropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system-properties")
public class SystemPropertiesController {

    private final SystemPropertiesService systemPropertiesService;

    @GetMapping("/get-commission")
    public Double getCommissionPercentage() {
        return systemPropertiesService.get().getIncomePercentage();
    }

    @PutMapping("/set-percentage/{percentage}")
    public void setCommissionPercentage(@PathVariable("percentage") Double percentage) {
        systemPropertiesService.updateComissionPrecentage(percentage);
    }
}
