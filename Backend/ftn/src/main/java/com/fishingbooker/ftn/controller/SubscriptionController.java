package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.CottageDto;
import com.fishingbooker.ftn.dto.InstructorSubscriptionDto;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import com.fishingbooker.ftn.service.interfaces.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    final private SubscriptionService subscriptionService;
    final private DataConverter converter;
    final private RegisteredClientService clientService;

    @PutMapping("/subscribe/{clientId}/{entityType}/{entityId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public void subscribe(@PathVariable Long clientId, @PathVariable String entityType, @PathVariable Long entityId) {
        subscriptionService.subscribe(clientId, entityType, entityId);
    }

    @PutMapping("/unsubscribe/{clientId}/{entityType}/{entityId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public void unsubscribe(@PathVariable Long clientId, @PathVariable String entityType, @PathVariable Long entityId) {
        subscriptionService.unsubscribe(clientId, entityType, entityId);
    }

    @GetMapping("/boats/{id}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public List<BoatDto> getClientBoatSubscriptions(@PathVariable Long id) {
        return clientService.getBoatSubscription(id);
    }

    @GetMapping("/cottages/{id}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public List<CottageDto> getClientCottageSubscriptions(@PathVariable Long id) {
        return clientService.getCottageSubscription(id);
    }

    @GetMapping("/instructors/{id}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public List<InstructorSubscriptionDto> getClientInstructorsSubscriptions(@PathVariable Long id) {
        return clientService.getClientInstructorSubscription(id);
    }

}
