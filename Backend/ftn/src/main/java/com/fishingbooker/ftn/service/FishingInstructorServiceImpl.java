package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ApplicationUserDto;
import com.fishingbooker.ftn.repository.FishingInstructorRepository;
import com.fishingbooker.ftn.service.interfaces.FishingInstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FishingInstructorServiceImpl implements FishingInstructorService {

    private final DataConverter converter;
    private final FishingInstructorRepository instructorRepository;

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
        return instructorRepository.save(instructor);
    }

}
