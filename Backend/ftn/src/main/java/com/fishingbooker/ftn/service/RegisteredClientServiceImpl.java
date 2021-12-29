package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.repository.AddressRepository;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisteredClientServiceImpl implements RegisteredClientService {

    private final RegisteredClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final DataConverter converter;

    @Override
    public List<RegisteredClientDto> findAll() {
        List<RegisteredClient> clientList = clientRepository.findAll();
        return converter.convert(clientList, RegisteredClientDto.class);
    }

    @Override
    public RegisteredClientDto findById(Long id) {
        RegisteredClient client = clientRepository.get(id);
        return converter.convert(client, RegisteredClientDto.class);
    }

    @Override
    public RegisteredClientDto findByEmail(String email) {
        RegisteredClientDto dto = null;
        for (RegisteredClient client : clientRepository.findAll()) {
            if (client.getEmail().equals(email)) {
                dto = converter.convert(client, RegisteredClientDto.class);
                break;
            }
        }
        return dto;
    }

    @Override
    public RegisteredClient create(ApplicationUserDto userDto) {
        RegisteredClient client = converter.convert(userDto, RegisteredClient.class);
        return clientRepository.save(client);
    }

    @Override
    public void update(RegisteredClientDto userDto) {
        RegisteredClient client = clientRepository.get(userDto.getId());
        client.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        client.setName(userDto.getName());
        client.setLastName(userDto.getLastName());
        client.setPhone(userDto.getPhone());
        Address clientAddress = client.getUserAddress();
        clientAddress.setStreet(userDto.getStreet());
        clientAddress.setCountry(userDto.getCountry());
        clientAddress.setCity(userDto.getCity());
        addressRepository.save(clientAddress);
        clientRepository.save(client);
    }

    @Override
    public List<RegisteredClient> getEnabledClients() {
        return clientRepository.getEnabledClients();
    }

    @Override
    public RegisteredClient delete(Long id) {
        RegisteredClient client = clientRepository.get(id);
        clientRepository.delete(client);
        return client;
    }

    @Override
    public List<Reservation> getPastCottageReservations(Long id) {
        RegisteredClient client = clientRepository.get(id);
        List<Reservation> reservations = new ArrayList<>();
        if (client != null) {
            for (CottageReservation cottageReservation : client.getCottageReservations())
                if (cottageReservation.getReservationStart().isBefore(LocalDate.now()))
                    reservations.add(cottageReservation);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getPastBoatReservations(Long id) {
        RegisteredClient client = clientRepository.get(id);
        List<Reservation> reservations = new ArrayList<>();
        if (client != null) {
            for (BoatReservation boatReservation : client.getBoatReservations())
                if (boatReservation.getReservationStart().isBefore(LocalDate.now()))
                    reservations.add(boatReservation);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getPastAdventureReservations(Long id) {
        RegisteredClient client = clientRepository.get(id);
        List<Reservation> reservations = new ArrayList<>();
        if (client != null) {
            for (AdventureReservation adventureReservation : client.getAdventureReservations())
                if (adventureReservation.getReservationStart().isBefore(LocalDate.now()))
                    reservations.add(adventureReservation);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getUpcomingReservations(Long id) {
        RegisteredClient client = clientRepository.get(id);
        List<Reservation> reservations = new ArrayList<>();
        if (client != null) {
            for (AdventureReservation adventureReservation : client.getAdventureReservations())
                if (adventureReservation.getReservationStart().isAfter(LocalDate.now()) || adventureReservation.getReservationStart().isEqual(LocalDate.now()))
                    reservations.add(adventureReservation);

            for (CottageReservation cottageReservation : client.getCottageReservations())
                if (cottageReservation.getReservationStart().isAfter(LocalDate.now()) || cottageReservation.getReservationStart().isEqual(LocalDate.now()))
                    reservations.add(cottageReservation);

            for (BoatReservation boatReservation : client.getBoatReservations())
                if (boatReservation.getReservationStart().isAfter(LocalDate.now()) || boatReservation.getReservationStart().isEqual(LocalDate.now()))
                    reservations.add(boatReservation);

        }

        return reservations;
    }

    @Override
    public RegisteredClient get(Long id) {
        return clientRepository.get(id);
    }

    @Override
    public List<BoatDto> getBoatSubscription(Long id) {
        RegisteredClient client = get(id);
        List<BoatDto> boats = new ArrayList<>();
        if (client != null) {
            for (Boat boat: client.getBoatSubscription())
                boats.add(converter.convert(boat, BoatDto.class));
        }
        return boats;
    }

    @Override
    public List<CottageDto> getCottageSubscription(Long id) {
        RegisteredClient client = get(id);
        List<CottageDto> cottages = new ArrayList<>();
        if (client != null) {
            for (Cottage cottage: client.getCottageSubscription())
                cottages.add(converter.convert(cottage, CottageDto.class));
        }
        return cottages;
    }

    @Override
    public List<InstructorSubscriptionDto> getClientInstructorSubscription(Long id) {
        RegisteredClient client = get(id);
        List<InstructorSubscriptionDto> instructors = new ArrayList<>();
        if (client != null) {
            for (FishingInstructor instructor: client.getInstructorSubscription())
                instructors.add(converter.convert(instructor, InstructorSubscriptionDto.class));
        }
        return instructors;
    }


}
