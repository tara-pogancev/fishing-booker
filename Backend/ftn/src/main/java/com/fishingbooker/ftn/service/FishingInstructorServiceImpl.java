package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.repository.FishingInstructorRepository;
import com.fishingbooker.ftn.service.interfaces.AddressService;
import com.fishingbooker.ftn.service.interfaces.FishingInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FishingInstructorServiceImpl implements FishingInstructorService {

    private final DataConverter converter;
    private final FishingInstructorRepository instructorRepository;
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
        instructorRepository.deleteById(id);
        return id;
    }

    @Override
    public FishingInstructor update(FishingInstructor instructor) {
        return instructorRepository.save(instructor);
    }

}
