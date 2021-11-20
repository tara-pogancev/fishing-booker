package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.FishingInstructorDto;
import com.fishingbooker.ftn.dto.RegisteredClientDto;
import com.fishingbooker.ftn.service.interfaces.FishingInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/fishing-instructor")
public class FishingInstructorController {


    private final FishingInstructorService fishingInstructorService;
    private final DataConverter converter;
    @GetMapping
    List<FishingInstructorDto> get(){
        List<FishingInstructor> insturctors=fishingInstructorService.findAll();
        List<FishingInstructorDto> instructorDtos=converter.convert(insturctors,FishingInstructorDto.class);
        return instructorDtos;
    }

    @GetMapping("/{id}")
    public FishingInstructorDto get(@PathVariable String id) {
        Long idNum = Long.parseLong(id);
        FishingInstructor instructor=fishingInstructorService.findById(idNum);
        return converter.convert(instructor,FishingInstructorDto.class);
    }
}
