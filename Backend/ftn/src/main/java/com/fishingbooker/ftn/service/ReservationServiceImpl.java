package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureUtility;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.UnixTimeToLocalDateTimeConverter;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.email.context.ClientReservationConfirmationEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final BoatService boatService;
    private final DateService dateService;
    private final EmailService emailService;
    private final CottageService cottageService;
    private final BoatRepository boatRepository;
    private final AdventureService adventureService;
    private final ReservationRepository reservationRepository;
    private final RegisteredClientRepository clientRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final QuickReservationRepository quickReservationRepository;
    private final CottageReservationRepository cottageReservationRepository;
    private final AdventureReservationRepository adventureReservationRepository;
    private final AdventureQuickReservationRepository adventureQuickReservationRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public CottageReservation bookCottage(ReservationDto reservationDto) {
        reservationDto.setEndDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(reservationDto.endDate));
        reservationDto.setStartDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(reservationDto.startDate));
        CottageReservation reservation = new CottageReservation();

        Cottage cottage = cottageService.get(reservationDto.entityId);
        RegisteredClient client = clientRepository.getById(reservationDto.userId);

        if (cottage != null && client != null
                && cottageService.isCottageAvailable(cottage, reservationDto.startDate, reservationDto.endDate)
                && !clientHasOverlappingReservation(reservationDto.startDate, reservationDto.endDate, client.getId())) {
            reservation.setReservationClient(client);
            reservation.setCottage(cottage);
            reservation.setReservationStart(reservationDto.startDate);
            reservation.setReservationEnd(reservationDto.endDate);
            reservation.setPrice(reservationDto.price);
            reservation.setGuestNumber(reservationDto.people);

            Set<CottageUtility> utilities = new HashSet<>();
            for (CottageUtility utility : cottage.getUtilities()) {
                if (reservationDto.utilityIds.contains(utility.getId()))
                    utilities.add(utility);
            }
            reservation.setUtilities(utilities);

            cottageReservationRepository.save(reservation);
            sendReservationConfirmationEmail(client, reservationDto);
            return reservation;
        }

        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AdventureReservation bookAdventure(ReservationDto reservationDto) {
        reservationDto.setEndDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(reservationDto.endDate));
        reservationDto.setStartDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(reservationDto.startDate));
        AdventureReservation reservation = new AdventureReservation();

        Adventure adventure = adventureService.get(reservationDto.entityId);
        RegisteredClient client = clientRepository.getById(reservationDto.userId);

        if (adventure != null && client != null
                && adventureService.isAdventureAvailable(adventure, reservationDto.startDate, reservationDto.endDate)
                && !clientHasOverlappingReservation(reservationDto.startDate, reservationDto.endDate, client.getId())) {
            reservation.setReservationClient(client);
            reservation.setAdventure(adventure);
            reservation.setReservationStart(reservationDto.startDate);
            reservation.setReservationEnd(reservationDto.endDate);
            reservation.setPrice(reservationDto.price);
            reservation.setGuestNumber(reservationDto.people);

            Set<AdventureUtility> utilities = new HashSet<>();
            for (AdventureUtility utility : adventure.getUtilities()) {
                if (reservationDto.utilityIds.contains(utility.getId()))
                    utilities.add(utility);
            }
            reservation.setUtilities(utilities);

            adventureReservationRepository.save(reservation);
            sendReservationConfirmationEmail(client, reservationDto);
            return reservation;
        }

        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BoatReservation bookBoat(ReservationDto reservationDto) {
        reservationDto.setEndDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(reservationDto.endDate));
        reservationDto.setStartDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(reservationDto.startDate));
        BoatReservation reservation = new BoatReservation();

        Boat boat = boatRepository.getById(reservationDto.entityId);
        RegisteredClient client = clientRepository.getById(reservationDto.userId);

        if (boat != null && client != null
                && boatService.isBoatAvailable(boat, reservationDto.startDate, reservationDto.endDate)
                && !clientHasOverlappingReservation(reservationDto.startDate, reservationDto.endDate, client.getId())) {
            reservation.setReservationClient(client);
            reservation.setBoat(boat);
            reservation.setReservationStart(reservationDto.startDate);
            reservation.setReservationEnd(reservationDto.endDate);
            reservation.setPrice(reservationDto.price);
            reservation.setGuestNumber(reservationDto.people);

            Set<BoatUtility> utilities = new HashSet<>();
            for (BoatUtility utility : boat.getUtilities()) {
                if (reservationDto.utilityIds.contains(utility.getId()))
                    utilities.add(utility);
            }
            reservation.setUtilities(utilities);

            boatReservationRepository.save(reservation);
            sendReservationConfirmationEmail(client, reservationDto);

            return reservation;
        }

        return null;
    }

    @Override
    public void cancel(Long reservationId) {
        Reservation reservation = reservationRepository.getById(reservationId);
        if (reservation != null) {
            reservation.setIsCanceled(true);
            reservationRepository.save(reservation);

            QuickReservation quickReservation = reservation.getQuickReservation();
            if (reservation.getQuickReservation() != null) {
                quickReservation.setIsReserved(false);
                quickReservationRepository.save(quickReservation);
            }
        }
    }

    @Override
    public List<Reservation> findAllByClient(Long id) {
        List<Reservation> reservations = new ArrayList<>();
        for (CottageReservation reservation : cottageReservationRepository.findAll()) {
            if (reservation.getReservationClient().getId() == id && !reservation.getIsCanceled())
                reservations.add(reservation);
        }

        for (BoatReservation reservation : boatReservationRepository.findAll()) {
            if (reservation.getReservationClient().getId() == id && !reservation.getIsCanceled())
                reservations.add(reservation);
        }

        for (AdventureReservation reservation : adventureReservationRepository.findAll()) {
            if (reservation.getReservationClient().getId() == id && !reservation.getIsCanceled())
                reservations.add(reservation);
        }

        return reservations;
    }

    @Override
    public Reservation find(Long id) {
        return reservationRepository.get(id);
    }

    @Override
    public CottageReservation findCottageReservation(Long id) {
        return cottageReservationRepository.get(id);
    }

    @Override
    public BoatReservation findBoatReservation(Long id) {
        return boatReservationRepository.get(id);
    }

    @Override
    public AdventureReservation findAdventureReservation(Long id) {
        return adventureReservationRepository.get(id);
    }

    public AdventureReservation getAdventureReservation(Long id) {
        return adventureReservationRepository.get(id);
    }

    @Override
    public AdventureQuickReservation getAdventureQuickReservation(Long id) {
        return adventureQuickReservationRepository.get(id);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getAllByClientWithCanceled(Long id) {
        List<Reservation> reservations = new ArrayList<>();
        for (CottageReservation reservation : cottageReservationRepository.findAll()) {
            if (reservation.getReservationClient().getId() == id)
                reservations.add(reservation);
        }

        for (BoatReservation reservation : boatReservationRepository.findAll()) {
            if (reservation.getReservationClient().getId() == id)
                reservations.add(reservation);
        }

        for (AdventureReservation reservation : adventureReservationRepository.findAll()) {
            if (reservation.getReservationClient().getId() == id)
                reservations.add(reservation);
        }

        return reservations;
    }

    @Override
    public List<Reservation> getInDate(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.getReservationsInDate(startDate, endDate);
    }

    public void sendReservationConfirmationEmail(ApplicationUser user, ReservationDto reservationDto) {
        ClientReservationConfirmationEmailContext emailContext = new ClientReservationConfirmationEmailContext();
        emailContext.init(user);
        emailContext.setReservationData(reservationDto);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Boolean clientHasOverlappingReservation(LocalDateTime start, LocalDateTime end, Long clientId) {
        RegisteredClient client = clientRepository.getById(clientId);
        if (client != null) {
            for (Reservation reservation : findAllByClient(clientId)) {
                if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), start, end))
                    return true;
            }
        }

        return false;
    }


}
