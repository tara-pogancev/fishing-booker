package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.dto.ReservationDto;
import com.fishingbooker.ftn.repository.CottageReservationRepository;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.interfaces.CottageService;
import com.fishingbooker.ftn.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final CottageReservationRepository cottageReservationRepository;
    private final RegisteredClientRepository clientRepository;
    private final CottageService cottageService;

    @Override
    public CottageReservation bookCottage(ReservationDto reservationDto) {
        CottageReservation reservation = new CottageReservation();

        Cottage cottage = cottageService.get(reservationDto.entityId);
        RegisteredClient client = clientRepository.getById(reservationDto.userId);

        if (cottage != null && client != null) {
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
        }

        return reservation;
    }


}
