package com.fishingbooker.ftn.service.interfaces;

public interface ActionReservationService {
    Long bookAction(Long clientId, Long actionId, String type);
}
