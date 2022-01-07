package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.adventures.*;
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
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.conversion.UnixTimeToLocalDateTimeConverter;
import com.fishingbooker.ftn.dto.ReservationDto;
import com.fishingbooker.ftn.email.context.ClientReservationConfirmationEmailContext;
import com.fishingbooker.ftn.email.service.EmailService;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.ActionReservationService;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ActionReservationServiceImpl implements ActionReservationService {

    private final EmailService emailService;
    private final DataConverter converter;
    private final RegisteredClientRepository clientRepository;
    private final QuickReservationRepository quickReservationRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final CottageReservationRepository cottageReservationRepository;
    private final AdventureReservationRepository adventureReservationRepository;
    private final BoatQuickReservationRepository boatQuickReservationRepository;
    private final CottageQuickReservationRepository cottageQuickReservationRepository;
    private final AdventureQuickReservationRepository adventureQuickReservationRepository;

    @Override
    public Long bookAction(Long clientId, Long actionId, String type) {
        RegisteredClient client = clientRepository.getById(clientId);
        QuickReservation action = quickReservationRepository.getById(actionId);

        if (client != null && action != null) {
            switch (type) {
                case "adventure": {
                    return bookAdventureAction(client, adventureQuickReservationRepository.getById(actionId)).getId();
                }
                case "boat": {
                    return bookBoatAction(client, boatQuickReservationRepository.getById(actionId)).getId();
                }
                case "cottage": {
                    return bookCottageAction(client, cottageQuickReservationRepository.getById(actionId)).getId();
                }
            }
        }
        return null;
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
