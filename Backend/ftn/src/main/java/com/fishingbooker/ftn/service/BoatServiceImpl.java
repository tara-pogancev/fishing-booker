package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.repository.BoatRepository;
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
                if ((period.getStartDate().isBefore(startDate) || period.getStartDate().isEqual(startDate))
                        && (period.getEndDate().isAfter(endDate) || period.getStartDate().isEqual(endDate))) {
                    boats.add(boat);
                    break;
                }
            }
        }
        return boats;
    }

    @Override
    public List<Boat> findFiltered(EntitySearchDto filterDto) {
        List<Boat> boats = new ArrayList<>();

        for (Boat boat : filterByDate(filterDto.startDate, filterDto.endDate)) {
            if (boat.getGuestLimit() >= filterDto.people && (boat.getAddress().getCountry().equals(filterDto.country) || filterDto.country.equals("")))
                boats.add(boat);
        }

        return boats;
    }

    @Override
    public Boat get(Long entityId) {
        return boatRepository.get(entityId);
    }


}
