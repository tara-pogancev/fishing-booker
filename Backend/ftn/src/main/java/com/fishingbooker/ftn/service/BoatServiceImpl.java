package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.conversion.UnixTimeToLocalDateTimeConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.repository.BoatRepository;
import com.fishingbooker.ftn.repository.BoatReservationRepository;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import com.fishingbooker.ftn.service.interfaces.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {

    private final DataConverter converter;
    private final DateService dateService;
    private final BoatRepository boatRepository;
    private final BoatReservationRepository boatReservationRepository;

    @Override
    public List<Boat> findAll() {
        List<Boat> boats=boatRepository.findAll();
        for(int i=0;i<boats.size();i++){
            if (boats.get(i).isDeleted()==true){
                boats.remove(i);
                i--;
            }
        }
        return boats;
    }

    @Override
    public BoatDto findById(long id) {
        Boat boat = boatRepository.getById(id);
        return converter.convert(boat, BoatDto.class);
    }

    @Override
    public Long delete(Long id) {
        Boat boat=boatRepository.getById(id);
        boat.setDeleted(true);
        boatRepository.save(boat);
        return boat.getId();
    }

    @Override
    public List<Boat> filterByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Boat> boats = new ArrayList<>();
        for (Boat boat : boatRepository.findAll()) {

            if (dateService.doDatesOverlapWithBoatPeriodSet(startDate, endDate, boat.getAvailableTimePeriods())) {
                // Passed availability check

                boolean reservationOverlap = false;
                for (BoatReservation reservation : getReservationsByBoat(boat.getId())) {
                    if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), startDate, endDate)) {
                        reservationOverlap = true;
                        break;
                    }
                }

                if (!reservationOverlap)
                    boats.add(boat);
            }

        }
        return boats;
    }

    @Override
    public List<Boat> findFiltered(EntitySearchDto filterDto, Long userId) {
        filterDto.setEndDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.endDate));
        filterDto.setStartDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.startDate));

        List<Boat> boats = new ArrayList<>();
        List<Boat> formerlyCanceled = getFormerlyCanceledBoatsByUserByDate(filterDto, userId);

        for (Boat boat : filterByDate(filterDto.startDate, filterDto.endDate)) {
            if (!isIdInList(formerlyCanceled, boat.getId()) && boat.getGuestLimit() >= filterDto.people && (boat.getAddress().getCountry().equals(filterDto.country) || filterDto.country.equals("")))
                boats.add(boat);
        }

        return boats;
    }

    @Override
    public Boolean isBoatAvailable(Boat boat, LocalDateTime start, LocalDateTime end) {
        boolean reservationOverlap = false;
        if (dateService.doDatesOverlapWithBoatPeriodSet(start, end, boat.getAvailableTimePeriods())) {
            // Passed availability check
            for (BoatReservation reservation : getReservationsByBoat(boat.getId())) {
                if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), start, end)) {
                    reservationOverlap = true;
                    break;
                }
            }
        }
        return !reservationOverlap;
    }

    private List<Boat> getFormerlyCanceledBoatsByUserByDate(EntitySearchDto filterDto, Long userId) {
        List<BoatReservation> canceled = boatReservationRepository.getClientCanceledBoatReservations(userId);
        List<Boat> formerlyCanceledBoats = new ArrayList<>();
        for (BoatReservation reservation : canceled) {
            if (dateService.doDatesMatchNearby(filterDto.startDate, filterDto.endDate, reservation.getReservationStart(), reservation.getReservationEnd())) {
                formerlyCanceledBoats.add(reservation.getBoat());
            }
        }
        return formerlyCanceledBoats;
    }

    private boolean isIdInList(List<Boat> list, Long id) {
        for (Boat boat : list) {
            if (boat.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boat get(Long entityId) {
        return boatRepository.get(entityId);
    }

    @Override
    public List<BoatReservation> getReservationsByBoat(Long boatId) {
        return boatReservationRepository.getBoatReservations(boatId);
    }


}
