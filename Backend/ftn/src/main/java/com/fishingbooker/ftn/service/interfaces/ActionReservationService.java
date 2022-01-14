package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.reservations.Reservation;

public interface ActionReservationService {

    Reservation bookAction(Long clientId, Long actionId, String type);

    Boolean hasCanceledAction(Long clientId, Long id);
}
