package com.fishingbooker.ftn.controller;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.RegisteredClientDto;
import com.fishingbooker.ftn.dto.ViewReservationDto;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final RegisteredClientService clientService;
    private final ReservationService reservationService;
    private final DataConverter converter;

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
    public List<ViewReservationDto> getPastCottageReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getPastCottageReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @GetMapping("/{id}/past-reservations/boats")
    public List<ViewReservationDto> getPastBoatReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getPastBoatReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @GetMapping("/{id}/past-reservations/adventures")
    public List<ViewReservationDto> getPastAdventureReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getPastAdventureReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @GetMapping("/{id}/upcoming-reservations")
    public List<ViewReservationDto> getUpcomingReservations(@PathVariable Long id) {
        List<Reservation> reservations = clientService.getUpcomingReservations(id);
        return converter.convert(reservations, ViewReservationDto.class);
    }

    @DeleteMapping("/{id}/cancel/{reservationId}")
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


}
