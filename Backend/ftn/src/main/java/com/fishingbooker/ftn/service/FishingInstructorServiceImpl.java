package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.repository.AdventureQuickReservationRepository;
import com.fishingbooker.ftn.repository.AdventureReservationRepository;
import com.fishingbooker.ftn.repository.FishingInstructorRepository;
import com.fishingbooker.ftn.service.interfaces.AddressService;
import com.fishingbooker.ftn.service.interfaces.FishingInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FishingInstructorServiceImpl implements FishingInstructorService {

    private final DataConverter converter;
    private final FishingInstructorRepository instructorRepository;
    private final AdventureReservationRepository adventureReservationRepository;
    private final AdventureQuickReservationRepository adventureQuickReservationRepository;
    private final AddressService addressService;

    @Override
    public List<FishingInstructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public FishingInstructor findById(Long id) {
        return instructorRepository.get(id);
    }

    @Override
    public FishingInstructor create(ApplicationUserDto userDto) {
        FishingInstructor instructor = converter.convert(userDto, FishingInstructor.class);
        Address userAddress = addressService.create(userDto);
        instructor.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        instructor.setUserAddress(userAddress);
        return instructorRepository.save(instructor);
    }

    @Override
    public List<FishingInstructor> getEnabledInstructors() {
        return instructorRepository.getEnabledInstructors();
    }

    @Override
    public Long delete(Long id) {

        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
        }
        return id;
    }

    @Override
    public FishingInstructor update(FishingInstructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<AdventureReservation> getInstructorReservations(Long id) {
        FishingInstructor instructor = instructorRepository.getById(id);
        List<AdventureReservation> ret = adventureReservationRepository.getInstructorReservations(id);
        return ret;
    }

    @Override
    public List<AdventureReservation> getNonCanceledInstructorReservations(Long id) {
        return adventureReservationRepository.getInstructorReservations(id);
    }

    @Override
    public List<AdventureQuickReservation> getInstructorQuickReservations(Long id) {
        FishingInstructor instructor = instructorRepository.getById(id);
        List<Adventure> adventures = new ArrayList<>(instructor.getAdventures());
        List<AdventureQuickReservation> ret = new ArrayList<>();
        for (Adventure adventure : adventures) {
            List<AdventureQuickReservation> quickReservations = new ArrayList<>(adventure.getAdventureQuickReservations());
            ret.addAll(quickReservations);
        }
        return ret;
    }

    @Override
    public List<AdventureReservation> getInstructorPastReservations(Long id) {
        List<AdventureReservation> adventureReservations = this.getInstructorReservations(id);
        for (int i = 0; i < adventureReservations.size(); i++) {
            if (adventureReservations.get(i).getReservationEnd().isAfter(LocalDateTime.now())) {
                adventureReservations.remove(i);
                i--;
            }
        }
        return adventureReservations;
    }

    @Override
    public List<AdventureQuickReservation> getInsturctorPastQuickReservations(Long id) {
        List<AdventureQuickReservation> adventureQuickReservations = this.getInstructorQuickReservations(id);
        for (int i = 0; i < adventureQuickReservations.size(); i++) {
            if (adventureQuickReservations.get(i).getActionEnd().isAfter(LocalDateTime.now())) {
                adventureQuickReservations.remove(i);
                i--;
            }
        }
        return adventureQuickReservations;
    }


}
