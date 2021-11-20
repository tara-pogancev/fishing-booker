package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.users.Administrator;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.AdministratorDto;
import com.fishingbooker.ftn.service.interfaces.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    private final AdministratorService adminService;
    private DataConverter converter;

    @GetMapping("/{id}")
    public Administrator get(@PathVariable("id") Long id){
        Administrator administrator=adminService.findById(id);
        return administrator;
    }
}
