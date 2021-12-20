package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.repository.AddressRepository;
import com.fishingbooker.ftn.repository.BoatOwnerRepository;
import com.fishingbooker.ftn.repository.BoatRepository;
import com.fishingbooker.ftn.repository.UtilityRepository;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {

    private final BoatRepository boatRepository;
    private final DataConverter converter;
    private final BoatOwnerRepository boatOwnerRepository;
    private final AddressRepository addressRepository;
    private final UtilityRepository utilityRepository;

    @Override
    public List<Boat> findAll() {
        return boatRepository.findAll();
    }

    @Override
    public BoatDto findById(long id) {
        Boat boat = boatRepository.getById(id);
        return converter.convert(boat, BoatDto.class);
    }

    @Override
    public Long delete(Long id) {
        boatRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Boat> filterByDate(LocalDate startDate, LocalDate endDate) {
        List<Boat> boats = new ArrayList<>();
        for (Boat boat : findAll()) {
            for (AvailableTimePeriod period : boat.getAvailableTimePeriods()) {
                if (period.getStartDate().isBefore(startDate) && period.getEndDate().isAfter(endDate)) {
                    boats.add(boat);
                    break;
                }
            }
        }
        return boats;
    }


}
