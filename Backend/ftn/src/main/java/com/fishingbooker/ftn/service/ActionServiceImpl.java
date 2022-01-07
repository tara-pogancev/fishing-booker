package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.bom.cottages.CottageQuickReservation;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.ActionDto;
import com.fishingbooker.ftn.repository.AdventureQuickReservationRepository;
import com.fishingbooker.ftn.repository.BoatQuickReservationRepository;
import com.fishingbooker.ftn.repository.CottageQuickReservationRepository;
import com.fishingbooker.ftn.service.interfaces.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final DataConverter converter;
    private final BoatQuickReservationRepository boatQuickReservationRepository;
    private final CottageQuickReservationRepository cottageQuickReservationRepository;
    private final AdventureQuickReservationRepository adventureQuickReservationRepository;


    @Override
    public List<CottageQuickReservation> findAllCottage() {
        List<CottageQuickReservation> retVal = new ArrayList<>();
        for (CottageQuickReservation reservation : cottageQuickReservationRepository.findAll()) {
            if (!reservation.getIsReserved() && reservation.getActionStart().isAfter(LocalDateTime.now()))
                retVal.add(reservation);
        }
        return retVal;
    }

    @Override
    public List<BoatQuickReservation> findAllBoat() {
        List<BoatQuickReservation> retVal = new ArrayList<>();
        for (BoatQuickReservation reservation : boatQuickReservationRepository.findAll()) {
            if (!reservation.getIsReserved() && reservation.getActionStart().isAfter(LocalDateTime.now()))
                retVal.add(reservation);
        }
        return retVal;
    }

    @Override
    public List<AdventureQuickReservation> findAllAdventure() {
        List<AdventureQuickReservation> retVal = new ArrayList<>();
        for (AdventureQuickReservation reservation : adventureQuickReservationRepository.findAll()) {
            if (!reservation.getIsReserved() && reservation.getActionStart().isAfter(LocalDateTime.now()))
                retVal.add(reservation);
        }
        return retVal;
    }

    @Override
    public List<ActionDto> findAll() {
        List<ActionDto> retVal = new ArrayList<>();
        for(AdventureQuickReservation action : findAllAdventure()) {
            retVal.add(converter.convert(action, ActionDto.class));
        }
        for(BoatQuickReservation action : findAllBoat()) {
            retVal.add(converter.convert(action, ActionDto.class));
        }
        for(CottageQuickReservation action : findAllCottage()) {
            retVal.add(converter.convert(action, ActionDto.class));
        }

        return retVal;
    }

}
