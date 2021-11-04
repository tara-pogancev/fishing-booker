package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        List<Address> dtoList = addressService.findAll();
        return new ResponseEntity<List<Address>>(dtoList, HttpStatus.OK);
    }

}
