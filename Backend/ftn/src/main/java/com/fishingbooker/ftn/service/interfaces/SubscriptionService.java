package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.users.RegisteredClient;

import java.util.List;

public interface SubscriptionService {

    void subscribe(Long clientId, String entityType, Long entityId);

    void unsubscribe(Long clientId, String entityType, Long entityId);

    List<RegisteredClient> getBoatSubscribers(Long boatId);

    List<RegisteredClient> cottageSubscribers(Long cottageId);

    List<RegisteredClient> instructorSubscribers(Long instructorId);

}
