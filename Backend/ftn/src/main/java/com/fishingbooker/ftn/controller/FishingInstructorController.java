package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.FishingInstructorDto;
import com.fishingbooker.ftn.service.interfaces.FishingInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/fishing-instructor")
public class FishingInstructorController {


    private final FishingInstructorService fishingInstructorService;
    private final DataConverter converter;

    @GetMapping
    List<FishingInstructorDto> get() {
        List<FishingInstructor> insturctors = fishingInstructorService.findAll();
        List<FishingInstructorDto> instructorDtos = converter.convert(insturctors, FishingInstructorDto.class);
        return instructorDtos;
    }

    @GetMapping("/{id}")
    public FishingInstructorDto get(@PathVariable String id) {
        Long idNum = Long.parseLong(id);
        FishingInstructor instructor = fishingInstructorService.findById(idNum);
        FishingInstructorDto dto = converter.convert(instructor, FishingInstructorDto.class);
        return dto;
    }

    @GetMapping("/get-enabled")
    public List<FishingInstructorDto> getEnabledInstructors() {
        List<FishingInstructor> instructors = fishingInstructorService.getEnabledInstructors();
        return converter.convert(instructors, FishingInstructorDto.class);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return fishingInstructorService.delete(id);
    }

    @PutMapping()
    public FishingInstructorDto changePersonalInfo(@RequestBody FishingInstructorDto instructorDto) {
        FishingInstructor instructor = fishingInstructorService.findById(instructorDto.getId());
        instructor.setPassword(new BCryptPasswordEncoder().encode(instructorDto.getPassword()));
        instructor.getUserAddress().setCountry(instructorDto.getCountry());
        instructor.getUserAddress().setStreet(instructorDto.getStreet());
        instructor.getUserAddress().setCity(instructorDto.getCity());
        instructor.setName(instructorDto.getName());
        instructor.setLastName(instructorDto.getLastName());
        instructor.setPhone(instructorDto.getPhone());
        instructor.setBiography(instructorDto.getBiography());
        return converter.convert(fishingInstructorService.update(instructor), FishingInstructorDto.class);
    }
}
