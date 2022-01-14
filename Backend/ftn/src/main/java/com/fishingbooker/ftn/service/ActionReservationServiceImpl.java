package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageQuickReservation;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.email.context.ClientReservationConfirmationEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.ActionReservationService;
import com.fishingbooker.ftn.service.interfaces.RegisteredClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ActionReservationServiceImpl implements ActionReservationService {

    private final EmailService emailService;
    private final RegisteredClientService clientService;
    private final RegisteredClientRepository clientRepository;
    private final QuickReservationRepository quickReservationRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final CottageReservationRepository cottageReservationRepository;
    private final AdventureReservationRepository adventureReservationRepository;
    private final BoatQuickReservationRepository boatQuickReservationRepository;
    private final CottageQuickReservationRepository cottageQuickReservationRepository;
    private final AdventureQuickReservationRepository adventureQuickReservationRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Reservation bookAction(Long clientId, Long actionId, String type) {
        RegisteredClient client = clientRepository.getById(clientId);
        QuickReservation action = quickReservationRepository.getById(actionId);

        if (client != null && action != null && !action.getIsReserved()
                && !clientService.clientHasOverlappingReservation(action.getActionStart(), action.getActionEnd(), clientId)) {
            switch (type) {
                case "adventure": {
                    return bookAdventureAction(client, adventureQuickReservationRepository.getById(actionId));
                }
                case "boat": {
                    return bookBoatAction(client, boatQuickReservationRepository.getById(actionId));
                }
                case "cottage": {
                    return bookCottageAction(client, cottageQuickReservationRepository.getById(actionId));
                }
            }
        }
        return null;
    }

    @Override
    public Boolean hasCanceledAction(Long clientId, Long id) {
        for (Reservation reservation : clientService.getAllClientReservations(clientId)) {
            if (reservation.getQuickReservation() != null && Objects.equals(reservation.getQuickReservation().getId(), id)) {
                return true;
            }
        }
        return false;
    }

    private AdventureReservation bookAdventureAction(RegisteredClient client, AdventureQuickReservation quickReservation) {
        AdventureReservation reservation = new AdventureReservation();

        Adventure adventure = quickReservation.getAdventure();
        if (adventure != null) {
            reservation.setReservationClient(client);
            reservation.setAdventure(adventure);
            reservation.setReservationStart(quickReservation.getActionStart());
            reservation.setReservationEnd(quickReservation.getActionEnd());
            reservation.setPrice(quickReservation.getPrice());
            reservation.setGuestNumber(quickReservation.getGuestLimit());
            // Utilities removed as they are contained in the action
            reservation.setQuickReservation(quickReservation);

            quickReservation.setIsReserved(true);
            quickReservation.setReservationClient(client);
            quickReservationRepository.save(quickReservation);
            adventureReservationRepository.save(reservation);
            sendReservationConfirmationEmail(client, getDtoForEmail(reservation, "Adventure"));
        }

        return reservation;
    }

    private BoatReservation bookBoatAction(RegisteredClient client, BoatQuickReservation quickReservation) {
        BoatReservation reservation = new BoatReservation();

        Boat boat = quickReservation.getBoat();
        if (boat != null) {
            reservation.setReservationClient(client);
            reservation.setBoat(boat);
            reservation.setReservationStart(quickReservation.getActionStart());
            reservation.setReservationEnd(quickReservation.getActionEnd());
            reservation.setPrice(quickReservation.getPrice());
            reservation.setGuestNumber(quickReservation.getGuestLimit());
            // Utilities removed as they are contained in the action
            reservation.setQuickReservation(quickReservation);

            quickReservation.setIsReserved(true);
            quickReservation.setReservationClient(client);
            quickReservationRepository.save(quickReservation);
            boatReservationRepository.save(reservation);
            sendReservationConfirmationEmail(client, getDtoForEmail(reservation, "Boat"));
        }

        return reservation;
    }

    private CottageReservation bookCottageAction(RegisteredClient client, CottageQuickReservation quickReservation) {
        CottageReservation reservation = new CottageReservation();

        Cottage cottage = quickReservation.getCottage();
        if (cottage != null) {
            reservation.setReservationClient(client);
            reservation.setCottage(cottage);
            reservation.setReservationStart(quickReservation.getActionStart());
            reservation.setReservationEnd(quickReservation.getActionEnd());
            reservation.setPrice(quickReservation.getPrice());
            reservation.setGuestNumber(quickReservation.getGuestLimit());
            // Utilities removed as they are contained in the action
            reservation.setQuickReservation(quickReservation);

            quickReservation.setIsReserved(true);
            quickReservation.setReservationClient(client);
            quickReservationRepository.save(quickReservation);
            cottageReservationRepository.save(reservation);
            sendReservationConfirmationEmail(client, getDtoForEmail(reservation, "Cottage"));
        }

        return reservation;
    }

    private ReservationDto getDtoForEmail(Reservation reservation, String type) {
        ReservationDto dto = new ReservationDto();
        dto.setPrice(reservation.getPrice());
        dto.setStartDate(reservation.getReservationStart());
        dto.setEntityType(type);
        return dto;
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
}
