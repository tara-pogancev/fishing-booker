package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.reservations.QuickReservation;

public interface ActionReservationService {
    Long bookAction(Long clientId, Long actionId, String type);
}
