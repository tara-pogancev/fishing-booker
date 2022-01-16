package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.service.interfaces.FishingInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/fishing-instructor")
public class FishingInstructorController {


    private final FishingInstructorService fishingInstructorService;
    private final DataConverter converter;

    @GetMapping
    List<FishingInstructorDto> get() {
        List<FishingInstructor> insturctors = fishingInstructorService.getEnabledInstructors();
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

    @GetMapping("/get-instructor-reservations/{id}")
    public List<InstructorCalendarReservationDto> getInstructorReservations(@PathVariable() Long id) {
        List<AdventureReservation> reservations = fishingInstructorService.getInstructorReservations(id);
        List<AdventureQuickReservation> quickReservations = fishingInstructorService.getInstructorQuickReservations(id);
        List<InstructorCalendarReservationDto> reservationDtos = converter.convert(reservations, InstructorCalendarReservationDto.class);
        List<InstructorCalendarReservationDto> quickReservationDtos = converter.convert(quickReservations, InstructorCalendarReservationDto.class);
        List<InstructorCalendarReservationDto> ret = new ArrayList<InstructorCalendarReservationDto>(reservationDtos);
        ret.addAll(quickReservationDtos);
        return ret;
    }

    @GetMapping("/get-instructor-past-reservations/{id}")
    public List<InstructorPastReservationsDto> getInstructorPastReservations(@PathVariable() Long id) {
        List<AdventureReservation> reservations = fishingInstructorService.getInstructorPastReservations(id);
        List<AdventureQuickReservation> quickReservations = fishingInstructorService.getInsturctorPastQuickReservations(id);
        List<InstructorPastReservationsDto> instructorPastReservationsDtos = converter.convert(reservations, InstructorPastReservationsDto.class);
        List<InstructorPastReservationsDto> instructorQuickPackReservations = converter.convert(quickReservations, InstructorPastReservationsDto.class);
        List<InstructorPastReservationsDto> ret = new ArrayList<>(instructorPastReservationsDtos);
        ret.addAll(instructorQuickPackReservations);
        return ret;
    }

    @GetMapping("instructor-business-report/{id}")
    public List<InstructorBusinessReportDto> getBusinessReport(@PathVariable() Long id) {
        List<AdventureReservation> reservations = fishingInstructorService.getInstructorPastReservations(id);
        List<InstructorBusinessReportDto> report = converter.convert(reservations, InstructorBusinessReportDto.class);
        return report;
    }

    @PutMapping("instructor-business-report")
    public List<InstructorBusinessReportDto> getReservationsInDate(@RequestBody DateRangeDto dateRangeDto) {
        List<AdventureReservation> reservations = fishingInstructorService.getReservationsInDate(dateRangeDto.getStartDate(), dateRangeDto.getEndDate(), dateRangeDto.getId());
        List<InstructorBusinessReportDto> report = converter.convert(reservations, InstructorBusinessReportDto.class);
        return report;
    }


}
