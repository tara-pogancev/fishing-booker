package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.dto.ReservationDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    CottageReservation bookCottage(ReservationDto reservationDto);

    AdventureReservation bookAdventure(ReservationDto reservationDto);

    BoatReservation bookBoat(ReservationDto reservationDto);

    void cancel(Long reservationId);

    List<Reservation> findAllByClient(Long id);

    Reservation find(Long id);

    CottageReservation findCottageReservation(Long id);

    BoatReservation findBoatReservation(Long id);

    AdventureReservation findAdventureReservation(Long id);

    AdventureReservation getAdventureReservation(Long id);

    AdventureQuickReservation getAdventureQuickReservation(Long id);

    List<Reservation> getAll();

    List<Reservation> getInDate(LocalDate startDate, LocalDate endDate);
}
