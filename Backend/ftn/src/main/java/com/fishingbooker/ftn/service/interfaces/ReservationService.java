package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.conversion.dto.ReservationDto;

public interface ReservationService {

    CottageReservation bookCottage(ReservationDto reservationDto);

    AdventureReservation bookAdventure(ReservationDto reservationDto);

    BoatReservation bookBoat(ReservationDto reservationDto);


}
