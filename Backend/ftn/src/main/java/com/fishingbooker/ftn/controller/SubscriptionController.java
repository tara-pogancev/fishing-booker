package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import com.fishingbooker.ftn.service.interfaces.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    final private SubscriptionService subscriptionService;
    final private DataConverter converter;
    final private RegisteredClientService clientService;

    @PutMapping("/subscribe/{clientId}/{entityType}/{entityId}")
    public void subscribe(@PathVariable Long clientId, @PathVariable String entityType, @PathVariable Long entityId ) {
        subscriptionService.subscribe(clientId, entityType, entityId);
    }

    @PutMapping("/unsubscribe/{clientId}/{entityType}/{entityId}")
    public void unsubscribe(@PathVariable Long clientId, @PathVariable String entityType, @PathVariable Long entityId ) {
        subscriptionService.unsubscribe(clientId, entityType, entityId);
    }

    @GetMapping("/boats/{id}")
    public List<BoatDto> getClientBoatSubscriptions(@PathVariable Long id) {
        return clientService.getBoatSubscription(id);
    }

    @GetMapping("/cottages/{id}")
    public List<CottageDto> getClientCottageSubscriptions(@PathVariable Long id) {
        //List<Cottage> cottages = (List<Cottage>) clientService.get(id).getCottageSubscription();
        //return converter.convert(cottages, CottageDto.class);
        return null;
    }

    @GetMapping("/instructors/{id}")
    public List<FishingInstructor> getClientInstructorsSubscriptions(@PathVariable Long id) {
        //List<FishingInstructor> instructors = (List<FishingInstructor>) clientService.get(id).getInstructorSubscription();
        //return converter.convert(instructors, FishingInstructor.class);
        return null;
    }

}
