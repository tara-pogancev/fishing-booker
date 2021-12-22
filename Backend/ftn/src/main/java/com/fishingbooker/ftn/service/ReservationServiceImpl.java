package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.dto.ReservationDto;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final BoatService boatService;
    private final CottageService cottageService;
    private final BoatRepository boatRepository;
    private final AdventureService adventureService;
    private final RegisteredClientRepository clientRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final CottageReservationRepository cottageReservationRepository;
    private final AdventureReservationRepository adventureReservationRepository;

    @Override
    public CottageReservation bookCottage(ReservationDto reservationDto) {
        CottageReservation reservation = new CottageReservation();

        Cottage cottage = cottageService.get(reservationDto.entityId);
        RegisteredClient client = clientRepository.getById(reservationDto.userId);

        if (cottage != null && client != null) {
            reservation.setReservationClient(client);
            reservation.setCottage(cottage);
            reservation.setReservationStart(reservationDto.startDate);
            reservation.setReservationEnd(reservationDto.endDate);
            reservation.setPrice(reservationDto.price);
            reservation.setGuestNumber(reservationDto.people);

            Set<CottageUtility> utilities = new HashSet<>();
            for (CottageUtility utility : cottage.getUtilities()) {
                if (reservationDto.utilityIds.contains(utility.getId()))
                    utilities.add(utility);
            }
            reservation.setUtilities(utilities);

            cottageReservationRepository.save(reservation);
        }

        return reservation;
    }

    @Override
    public AdventureReservation bookAdventure(ReservationDto reservationDto) {
        AdventureReservation reservation = new AdventureReservation();

        Adventure adventure = adventureService.get(reservationDto.entityId);
        RegisteredClient client = clientRepository.getById(reservationDto.userId);

        if (adventure != null && client != null) {
            reservation.setReservationClient(client);
            reservation.setAdventure(adventure);
            reservation.setReservationStart(reservationDto.startDate);
            reservation.setReservationEnd(reservationDto.endDate);
            reservation.setPrice(reservationDto.price);
            reservation.setGuestNumber(reservationDto.people);

            Set<AdventureUtility> utilities = new HashSet<>();
            for (AdventureUtility utility : adventure.getUtilities()) {
                if (reservationDto.utilityIds.contains(utility.getId()))
                    utilities.add(utility);
            }
            reservation.setUtilities(utilities);

            adventureReservationRepository.save(reservation);
        }

        return reservation;
    }

    @Override
    public BoatReservation bookBoat(ReservationDto reservationDto) {
        BoatReservation reservation = new BoatReservation();

        Boat boat = boatRepository.getById(reservationDto.entityId);
        RegisteredClient client = clientRepository.getById(reservationDto.userId);

        if (boat != null && client != null) {
            reservation.setReservationClient(client);
            reservation.setBoat(boat);
            reservation.setReservationStart(reservationDto.startDate);
            reservation.setReservationEnd(reservationDto.endDate);
            reservation.setPrice(reservationDto.price);
            reservation.setGuestNumber(reservationDto.people);

            Set<BoatUtility> utilities = new HashSet<>();
            for (BoatUtility utility : boat.getUtilities()) {
                if (reservationDto.utilityIds.contains(utility.getId()))
                    utilities.add(utility);
            }
            reservation.setUtilities(utilities);

            boatReservationRepository.save(reservation);
        }

        return reservation;
    }


}
