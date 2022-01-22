package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.boats.*;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.bom.boats.AvailableBoatTimePeriod;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatQuickReservation;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.conversion.UnixTimeToLocalDateTimeConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {

    private final DataConverter converter;
    private final DateService dateService;
    private final BoatRepository boatRepository;
    private final BoatReservationRepository boatReservationRepository;
    private final BoatOwnerRepository boatOwnerRepository;
    private final ImageService imageService;
    private final RuleOfConductService ruleOfConductService;
    private final UtilityService utilityService;
    private final BoatTypeRepository boatTypeRepository;
    private final NavigationalEquipmentRepository navigationalEquipmentRepository;
    private SubscriptionService subscriptionService;
    @Autowired
    public void setSubscriptionService(@Lazy SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    private final MailingService mailingService;
    private final BoatQuickReservationRepository boatQuickReservationRepository;
    private final AvailableBoatTimePeriodRepository availableBoatTimePeriodRepository;
    private final RegisteredClientRepository clientRepository;

    @Override
    public List<Boat> findAll() {
        List<Boat> boats = boatRepository.findAll();
        for (int i = 0; i < boats.size(); i++) {
            if (boats.get(i).isDeleted() == true) {
                boats.remove(i);
                i--;
            }
        }
        return boats;
    }

    @Override
    public BoatDto findById(long id) {
        Boat boat = boatRepository.getById(id);
        return converter.convert(boat, BoatDto.class);
    }

    @Override
    public Boolean delete(Long id) {
        Boat boat = boatRepository.getById(id);
        if ((boat.getBoatReservations() == null || boat.getBoatReservations().size() == 0)
                && (boat.getBoatQuickReservations() == null || boat.getBoatQuickReservations().size() == 0)) {
            boat.setDeleted(true);
            boatRepository.save(boat);
            return true;
        }
        return false;
    }

    @Override
    public List<Boat> filterByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Boat> boats = new ArrayList<>();
        for (Boat boat : boatRepository.findAll()) {

            if (dateService.doDatesOverlapWithBoatPeriodSet(startDate, endDate, boat.getAvailableTimePeriods())) {
                // Passed availability check

                boolean reservationOverlap = false;
                for (BoatReservation reservation : getReservationsByBoat(boat.getId())) {
                    if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), startDate, endDate)) {
                        reservationOverlap = true;
                        break;
                    }
                }

                if (!reservationOverlap)
                    boats.add(boat);
            }

        }
        return boats;
    }

    @Override
    public List<Boat> findFiltered(EntitySearchDto filterDto, Long userId) {
        filterDto.setEndDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.endDate));
        filterDto.setStartDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.startDate));

        List<Boat> boats = new ArrayList<>();
        List<Boat> formerlyCanceled = getFormerlyCanceledBoatsByUserByDate(filterDto, userId);

        for (Boat boat : filterByDate(filterDto.startDate, filterDto.endDate)) {
            if (!isIdInList(formerlyCanceled, boat.getId()) && boat.getGuestLimit() >= filterDto.people && (boat.getAddress().getCountry().equals(filterDto.country) || filterDto.country.equals("")))
                boats.add(boat);
        }

        return boats;
    }

    @Override
    public Boolean isBoatAvailable(Boat boat, LocalDateTime start, LocalDateTime end) {
        boolean reservationOverlap = false;
        if (dateService.doDatesOverlapWithBoatPeriodSet(start, end, boat.getAvailableTimePeriods())) {
            // Passed availability check
            for (BoatReservation reservation : getReservationsByBoat(boat.getId())) {
                if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), start, end)) {
                    reservationOverlap = true;
                    break;
                }
            }
        }
        return !reservationOverlap;
    }

    private List<Boat> getFormerlyCanceledBoatsByUserByDate(EntitySearchDto filterDto, Long userId) {
        List<BoatReservation> canceled = boatReservationRepository.getClientCanceledBoatReservations(userId);
        List<Boat> formerlyCanceledBoats = new ArrayList<>();
        for (BoatReservation reservation : canceled) {
            if (dateService.doDatesMatchNearby(filterDto.startDate, filterDto.endDate, reservation.getReservationStart(), reservation.getReservationEnd())) {
                formerlyCanceledBoats.add(reservation.getBoat());
            }
        }
        return formerlyCanceledBoats;
    }

    private boolean isIdInList(List<Boat> list, Long id) {
        for (Boat boat : list) {
            if (boat.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boat get(Long entityId) {
        return boatRepository.get(entityId);
    }

        @Override
    public List<BoatReservation> getReservationsByBoat(Long boatId) {
        return boatReservationRepository.getBoatReservations(boatId);
    }

    @Override
    public List<BoatDto> findByBoatOwnerId(long id) {
        return converter.convert(boatRepository.findByBoatOwnerId(id), BoatDto.class);
    }

    @Override
    public Long create(BoatCreationDto boatDto) {
        Boat boat;
        if (boatDto.getId() == -1) {
            boat = new Boat();
        } else {
            boat = boatRepository.getById(boatDto.getId());
        }

        BoatOwner boatOwner = boatOwnerRepository.getById(boatDto.getOwnerId());
        Address address = new Address();
        boat.setName(boatDto.getName());
        address.setCity(boatDto.getCity());
        address.setCountry(boatDto.getCountry());
        address.setStreet(boatDto.getStreet());
        boat.setAddress(address);
        boat.setPrice(boatDto.getPrice());
        boat.setBoatOwner(boatOwner);
        boat.setBoatLength(boatDto.getBoatLength());
        boat.setBoatType(boatDto.getBoatType());
        boat.setEnginePower(boatDto.getEnginePower());
        boat.setNumberOfEngines(boatDto.getNumberOfEngines());
        boat.setFishingEquipment(boatDto.getFishingEquipment());
        boat.setMaxSpeed(boatDto.getMaxSpeed());
        boat.setCancellationPercentageKeep(boatDto.getCancellationPercentageKeep());
        List<RuleOfConduct> rules = ruleOfConductService.createRulesFromString(boatDto.getRules());
        boat.setRules(new HashSet<>(rules));
        boat.setDescription(boatDto.getDescription());
        boat.setGuestLimit(boatDto.getGuestLimit());
        try {
            boat.setImages(imageService.saveImages(boatDto.getImages()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boat savedBoat = boatRepository.save(boat);
        savedBoat.setNavigationalEquipments(boatDto.getNavigationalEquipments().stream().map(navEq -> getNavigationalEquipmentByName(navEq)).collect(Collectors.toSet()));
        Set<BoatUtility> utilities = utilityService.convertStringToUtility(boatDto.getAdditionalServices(), savedBoat);
        
        savedBoat.setUtilities(utilities);
        return boatRepository.save(savedBoat).getId();

    }

    @Override
    public List<BoatType> getBoatTypes() {
        return boatTypeRepository.findAll();
    }

    @Override
    public List<NavigationalEquipment> getNavigationalEquipment() {
        return navigationalEquipmentRepository.findAll();
    }

    @Override
    public Long createQuickReservation(AdventureQuickReservationDto dto) {
        Boat boat = boatRepository.getById(dto.getAdventureId());
        if (!validate(boat, dto.getActionStart(), dto.getActionEnd())) {
            return -1l;
        } else {
            BoatQuickReservation boatQuickReservation = new BoatQuickReservation();
            boatQuickReservation.setBoat(boat);
            boatQuickReservation.setActionStart(dto.getActionStart());
            boatQuickReservation.setActionEnd(dto.getActionEnd());
            boatQuickReservation.setGuestLimit(dto.getGuestLimit());
            boatQuickReservation.setPrice(dto.getPrice());
            boatQuickReservation.setUtilities(utilityService.convertUtilityDtoToUtility(dto.getAdventureUtilityDtoList()));
            boatQuickReservation.recalculateFullPrice();
            BoatQuickReservation persistedReservation = boatQuickReservationRepository.save(boatQuickReservation);
            List<RegisteredClient> clients = subscriptionService.getBoatSubscribers(persistedReservation.getBoat().getBoatOwner().getId());
            for (RegisteredClient client : clients) {
                mailingService.sendMailToSubscribedUsers(client, persistedReservation.getReservationClient().getFullName());
            }
            return persistedReservation.getId();
        }
    }

    @Override
    public List<BoatUtility> getBoatUtilities(Long id) {
        Boat boat = boatRepository.getById(id);
        return new ArrayList<>(boat.getUtilities());
    }

    @Override
    public Long createReservation(NewReservationDto dto) {
        Boat boat = boatRepository.getById(dto.getEntityId());
        if (!validate(boat, UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()), UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()))) {
            return -1L;
        } else {
            BoatReservation boatReservation = new BoatReservation();
            boatReservation.setBoat(boat);
            boatReservation.setReservationClient(clientRepository.getById(dto.getClientId()));
            boatReservation.setReservationStart(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()));
            boatReservation.setReservationEnd(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()));
            boatReservation.setGuestNumber(dto.getGuestNumber());
            boatReservation.setPrice(dto.getPrice());
            boatReservation.setGuestNumber(dto.getGuestNumber());
            boatReservation.setPrice(dto.getPrice());
            Set<BoatUtility> utilities = utilityService.convertStringToUtility(dto.getUtilities(), boat);
            boatReservation.setUtilities(utilities);
            mailingService.sendMailToUserAboutNewReservation(boat.getBoatOwner(),clientRepository.getById(dto.getClientId()));
            return boatReservationRepository.save(boatReservation).getId();
        }
    }

    @Override
    public void deleteByAdmin(Long id) {
        Boat boat=boatRepository.getById(id);
        boat.setDeleted(true);
        boatRepository.save(boat);
    }

    private boolean validate(Boat boat, LocalDateTime startDate, LocalDateTime endDate) {
        List<BoatQuickReservation> quickReservations = boatQuickReservationRepository.getOverlappedWithNewAction(startDate, endDate, boat.getId());
        if (quickReservations.size() != 0) { //vec postoji kreiranja brza rezervacija u ovom periodu
            return false;
        }
        List<BoatReservation> boatReservations = boatReservationRepository.getOverlappedWithNewAction(startDate, endDate, boat.getId());
        if (boatReservations.size() != 0) {//vec postoji kreirana obicna rezervacija u ovom periodu
            return false;
        }
        List<AvailableBoatTimePeriod> periods = availableBoatTimePeriodRepository.getAvailabilityForDate(startDate, endDate, boat.getId());
        if (periods.size() == 0) { //znaci da vikendica nije dostupna za vrijeme kreiranja
            return false;
        }

        return true;
    }

    private NavigationalEquipment getNavigationalEquipmentByName(String name) {
        List<NavigationalEquipment> navigationalEquipments = navigationalEquipmentRepository.findAll();
        return navigationalEquipments.stream()
                .filter(navigationalEquipment -> name.equals(navigationalEquipment.getName().toString()))
                .findAny()
                .orElse(null);

    }

}
