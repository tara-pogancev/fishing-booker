package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.EntitySearchDto;
import com.fishingbooker.ftn.dto.RegisteredClientDto;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import com.fishingbooker.ftn.service.interfaces.ActionReservationService;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final DataConverter converter;
    private final RegisteredClientService clientService;
    private final ReservationService reservationService;
    final private ActionReservationService actionReservationService;

    @GetMapping
    public List<RegisteredClientDto> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/email/{email}")
    public RegisteredClientDto findByEmail(@PathVariable String email) {
        return clientService.findByEmail(email);
    }

    @GetMapping("/{id}")
    public RegisteredClientDto findById(@PathVariable String id) {
        Long idNum = Long.parseLong(id);
        return clientService.findById(idNum);
    }

    @PutMapping
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public void update(@RequestBody RegisteredClientDto dto) {
        clientService.update(dto);
    }

    @GetMapping("/get-enabled")
    public List<RegisteredClientDto> getEnabledClients() {
        List<RegisteredClient> clients = clientService.getEnabledClients();
        return converter.convert(clients, RegisteredClientDto.class);
    }

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<Long> deleteClient(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.delete(id).getId(), HttpStatus.OK);
    }

    @GetMapping("/{id}/past-reservations/cottages")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public List<ViewReservationDto> getPastCottageReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getPastCottageReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @GetMapping("/{id}/past-reservations/boats")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public List<ViewReservationDto> getPastBoatReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getPastBoatReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @GetMapping("/{id}/past-reservations/adventures")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public List<ViewReservationDto> getPastAdventureReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getPastAdventureReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @GetMapping("/{id}/upcoming-reservations")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public List<ViewReservationDto> getUpcomingReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getUpcomingReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @DeleteMapping("/{id}/cancel/{reservationId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public ResponseEntity cancelReservation(@PathVariable Long id, @PathVariable Long reservationId) {
        reservationService.cancel(reservationId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users-with-reservation/{id}")
    public List<RegisteredClientDto> getClientsWithReservation(@PathVariable("id") Long instructorId) {
        List<RegisteredClient> clients = clientService.getClientsWithReservation(instructorId);
        List<RegisteredClientDto> clientDtos = converter.convert(clients, RegisteredClientDto.class);
        return clientDtos;
    }

    @GetMapping("/users-with-cottage-reservation/{id}")
    public List<RegisteredClientDto> getClientsWithCottageReservation(@PathVariable("id") Long cottageOwnerId) {
        List<RegisteredClient> clients = clientService.getClientsWithCottageReservation(cottageOwnerId);
        List<RegisteredClientDto> clientDtos = converter.convert(clients, RegisteredClientDto.class);
        return clientDtos;
    }

    @PostMapping("/book-action/{clientId}/{type}/{actionId}")
    public ResponseEntity<Long> bookAction(@PathVariable Long clientId, @PathVariable Long actionId, @PathVariable String type) {
        Reservation reservation = actionReservationService.bookAction(clientId, actionId, type);
        if (reservation == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(reservation.getId(), HttpStatus.OK);
        }
    }

    @PostMapping("/client-dates-overlap/{userId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public Boolean clientDatesOverlap(@PathVariable Long userId, @RequestBody EntitySearchDto filterDto) {
        return clientService.clientHasOverlappingReservation(filterDto.startDate, filterDto.endDate, userId);
    }

    @GetMapping("/canceled-action/{clientId}/{actionId}")
    @PreAuthorize("hasRole('REGISTERED_CLIENT')")
    public Boolean isActionCanceledByClient(@PathVariable Long clientId, @PathVariable Long actionId) {
        return actionReservationService.hasCanceledAction(clientId, actionId);
    }

}
