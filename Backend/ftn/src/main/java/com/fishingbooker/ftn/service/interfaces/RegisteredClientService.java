package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface RegisteredClientService {

    List<RegisteredClientDto> findAll();

    RegisteredClientDto findById(Long id);

    RegisteredClientDto findByEmail(String email);

    RegisteredClient create(ApplicationUserDto userDto);

    void update(RegisteredClientDto userDto);

    List<RegisteredClient> getEnabledClients();

    RegisteredClient delete(Long id);

    List<Reservation> getPastCottageReservations(Long id);

    List<Reservation> getPastBoatReservations(Long id);

    List<Reservation> getPastAdventureReservations(Long id);

    List<Reservation> getUpcomingReservations(Long id);

    RegisteredClient get(Long id);

    List<BoatDto> getBoatSubscription(Long id);

    List<CottageDto> getCottageSubscription(Long id);

    List<InstructorSubscriptionDto> getClientInstructorSubscription(Long id);

    List<RegisteredClient> getClientsWithReservation(Long instructorId);

    Boolean clientHasOverlappingReservation(LocalDateTime start, LocalDateTime end, Long clientId);

}
