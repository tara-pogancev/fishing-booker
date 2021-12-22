package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.conversion.dto.ReservationDto;

public interface ReservationService {

    CottageReservation bookCottage(ReservationDto reservationDto);

}
