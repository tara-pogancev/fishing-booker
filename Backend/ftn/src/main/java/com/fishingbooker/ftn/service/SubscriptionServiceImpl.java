package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.BoatService;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import com.fishingbooker.ftn.service.interfaces.FishingInstructorService;
import com.fishingbooker.ftn.service.interfaces.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    final private RegisteredClientRepository clientRepository;
    final private BoatService boatService;
    final private CottageService cottageService;
    final private FishingInstructorService fishingInstructorService;

    @Override
    public void subscribe(Long clientId, String entityType, Long entityId) {
        RegisteredClient client = clientRepository.get(clientId);
        if (client != null) {
            switch (entityType) {
                case "cottage": {
                    Cottage cottage = cottageService.get(entityId);
                    if (cottage != null) {
                        client.getCottageSubscription().add(cottage);
                        clientRepository.save(client);
                    }
                    break;
                }
                case "boat": {
                    Boat boat = boatService.get(entityId);
                    if (boat != null) {
                        client.getBoatSubscription().add(boat);
                        clientRepository.save(client);
                    }
                    break;
                }
                case "adventure": {
                    FishingInstructor instructor = fishingInstructorService.findById(entityId);
                    if (instructor != null) {
                        client.getInstructorSubscription().add(instructor);
                        clientRepository.save(client);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void unsubscribe(Long clientId, String entityType, Long entityId) {
        RegisteredClient client = clientRepository.get(clientId);
        if (client != null) {
            switch (entityType) {
                case "cottage": {
                    for (Cottage cottage : client.getCottageSubscription()) {
                        if (cottage.getId() == entityId) {
                            client.getCottageSubscription().remove(cottage);
                            clientRepository.save(client);
                            break;
                        }
                    }
                    break;
                }
                case "boat": {
                    for (Boat boat : client.getBoatSubscription()) {
                        if (boat.getId() == entityId) {
                            client.getBoatSubscription().remove(boat);
                            clientRepository.save(client);
                            break;
                        }
                    }
                    break;
                }
                case "adventure": {
                    for (FishingInstructor instructor : client.getInstructorSubscription()) {
                        if (instructor.getId() == entityId) {
                            client.getInstructorSubscription().remove(instructor);
                            clientRepository.save(client);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    @Override
    public List<RegisteredClient> getBoatSubscribers(Long boatId) {
        List<RegisteredClient> clients = new ArrayList<>();
        for (RegisteredClient client : clientRepository.findAll()) {
            for (Boat boat : client.getBoatSubscription()) {
                if (boat.getId() == boatId) {
                    clients.add(client);
                    break;
                }
            }
        }

        return clients;
    }

    @Override
    public List<RegisteredClient> getCottageSubscribers(Long cottageId) {
        List<RegisteredClient> clients = new ArrayList<>();
        for (RegisteredClient client : clientRepository.findAll()) {
            for (Cottage cottage : client.getCottageSubscription()) {
                if (cottage.getId() == cottageId) {
                    clients.add(client);
                    break;
                }
            }
        }

        return clients;
    }

    @Override
    public List<RegisteredClient> getInstructorSubscribers(Long instructorId) {
        List<RegisteredClient> clients = new ArrayList<>();
        for (RegisteredClient client : clientRepository.findAll()) {
            for (FishingInstructor instructor : client.getInstructorSubscription()) {
                if (instructor.getId() == instructorId) {
                    clients.add(client);
                    break;
                }
            }
        }

        return clients;
    }

}
