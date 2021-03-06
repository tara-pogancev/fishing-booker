package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.cottages.*;
import com.fishingbooker.ftn.bom.users.CottageOwner;
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
public class CottageServiceImpl implements CottageService {

    private final DateService dateService;
    private final DataConverter converter;
    private final ImageService imageService;
    private final UtilityService utilityService;
    private final CottageRepository cottageRepository;
    private final RuleOfConductService ruleOfConductService;
    private final CottageOwnerRepository cottageOwnerRepository;
    private final CottageReservationRepository cottageReservationRepository;
    private final RoomRepository roomRepository;
    private SubscriptionService subscriptionService;
    @Autowired
    public void setSubscriptionService(@Lazy SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    private final MailingService mailingService;
    private final CottageQuickReservationRepository cottageQuickReservationRepository;
    private final AvailableCottageTimePeriodRepository availableCottageTimePeriodRepository;
    private final RegisteredClientRepository clientRepository;

    @Override
    public List<Cottage> findAll() {
        return cottageRepository.getCottages();
    }

    @Override
    public CottageDto findById(long id) {
        return converter.convert(cottageRepository.getById(id), CottageDto.class);
    }

    @Override
    public Cottage get(Long id) {
        return cottageRepository.get(id);
    }

    @Override
    public List<CottageDto> findByCottageOwnerId(long id) {
        return converter.convert(cottageRepository.findByCottageOwnerId(id), CottageDto.class);
    }

    @Override
    public Long create(CottageCreationDto cottageDto) {
        Cottage cottage;
        if (cottageDto.getId() == -1) {
            cottage = new Cottage();
        } else {
            cottage = cottageRepository.getById(cottageDto.getId());
        }

        CottageOwner cottageOwner = cottageOwnerRepository.getById(cottageDto.getOwnerId());
        Address address = new Address();
        cottage.setName(cottageDto.getName());
        address.setCity(cottageDto.getCity());
        address.setCountry(cottageDto.getCountry());
        address.setStreet(cottageDto.getStreet());
        cottage.setAddress(address);
        cottage.setPrice(cottageDto.getPrice());
        cottage.setCottageOwner(cottageOwner);
        List<RuleOfConduct> rules = ruleOfConductService.createRulesFromString(cottageDto.getRules());
        cottage.setRules(new HashSet<>(rules));
        cottage.setDescription(cottageDto.getDescription());
        cottage.setGuestLimit(cottageDto.getGuestLimit());
        try {
            cottage.setImages(imageService.saveImages(cottageDto.getImages()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cottage savedCottage = cottageRepository.save(cottage);
        roomRepository.deleteRoomsByCottageId(cottage.getId());
        savedCottage.setRooms(convertDtoToModel(cottageDto.getRooms(), savedCottage));
        Set<CottageUtility> utilities = utilityService.convertStringToUtility(cottageDto.getAdditionalServices(), savedCottage);
        savedCottage.setUtilities(utilities);
        return cottageRepository.save(savedCottage).getId();
    }

    @Override
    public List<Cottage> filterByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Cottage> cottages = new ArrayList<>();
        for (Cottage cottage : cottageRepository.findAll()) {

            if (dateService.doDatesOverlapWithCottagePeriodSet(startDate, endDate, cottage.getAvailableTimePeriods())) {
                // Passed availability check

                boolean reservationOverlap = false;
                for (CottageReservation reservation : getReservationsByCottage(cottage.getId())) {
                    if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), startDate, endDate)) {
                        reservationOverlap = true;
                        break;
                    }
                }

                if (!reservationOverlap)
                    cottages.add(cottage);
            }

        }
        return cottages;
    }

    @Override
    public List<Cottage> findFiltered(EntitySearchDto filterDto, Long userId) {
        filterDto.setEndDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.endDate));
        filterDto.setStartDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.startDate));

        List<Cottage> cottages = new ArrayList<>();
        List<Cottage> formerlyCanceled = getFormerlyCanceledCottagesByUserByDate(filterDto, userId);

        for (Cottage cottage : filterByDate(filterDto.startDate, filterDto.endDate)) {
            if (!isIdInList(formerlyCanceled, cottage.getId()) && cottage.getGuestLimit() >= filterDto.people && (cottage.getAddress().getCountry().equals(filterDto.country) || filterDto.country.equals("")))
                cottages.add(cottage);
        }

        return cottages;
    }

    private List<Cottage> getFormerlyCanceledCottagesByUserByDate(EntitySearchDto filterDto, Long userId) {
        List<CottageReservation> canceled = cottageReservationRepository.getClientCanceledCottageReservations(userId);
        List<Cottage> formerlyCanceledCottages = new ArrayList<>();
        for (CottageReservation reservation : canceled) {
            if (dateService.doDatesMatchNearby(filterDto.startDate, filterDto.endDate, reservation.getReservationStart(), reservation.getReservationEnd())) {
                formerlyCanceledCottages.add(reservation.getCottage());
            }
        }
        return formerlyCanceledCottages;
    }

    private boolean isIdInList(List<Cottage> list, Long id) {
        for (Cottage cottage : list) {
            if (cottage.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CottageReservation> getReservationsByCottage(Long cottageId) {
        return cottageReservationRepository.getCottageReservations(cottageId);
    }

    @Override
    public Boolean isCottageAvailable(Cottage cottage, LocalDateTime start, LocalDateTime end) {
        boolean reservationOverlap = false;
        if (dateService.doDatesOverlapWithCottagePeriodSet(start, end, cottage.getAvailableTimePeriods())) {
            // Passed availability check
            for (CottageReservation reservation : getReservationsByCottage(cottage.getId())) {
                if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), start, end)) {
                    reservationOverlap = true;
                    break;
                }
            }
        }
        return !reservationOverlap;
    }

    @Override
    public Long createQuickReservation(AdventureQuickReservationDto dto) {
        Cottage cottage = cottageRepository.getById(dto.getAdventureId());
        if (!validate(cottage, dto.getActionStart(), dto.getActionEnd())) {
            return -1l;
        } else {
            CottageQuickReservation cottageQuickReservation = new CottageQuickReservation();
            cottageQuickReservation.setCottage(cottage);
            cottageQuickReservation.setActionStart(dto.getActionStart());
            cottageQuickReservation.setActionEnd(dto.getActionEnd());
            cottageQuickReservation.setGuestLimit(dto.getGuestLimit());
            cottageQuickReservation.setPrice(dto.getPrice());
            cottageQuickReservation.setUtilities(utilityService.convertUtilityDtoToUtility(dto.getAdventureUtilityDtoList()));
            cottageQuickReservation.recalculateFullPrice();
            CottageQuickReservation persistedReservation = cottageQuickReservationRepository.save(cottageQuickReservation);
            List<RegisteredClient> clients = subscriptionService.getCottageSubscribers(persistedReservation.getCottage().getCottageOwner().getId());
            for (RegisteredClient client : clients) {
                mailingService.sendMailToSubscribedUsers(client, persistedReservation.getReservationClient().getFullName());
            }
            return persistedReservation.getId();
        }
    }

    @Override
    public List<CottageUtility> getCottageUtilities(Long id) {
        Cottage cottage = cottageRepository.getById(id);
        return new ArrayList<>(cottage.getUtilities());
    }

    @Override
    public Long createReservation(NewReservationDto dto) {
        Cottage cottage = cottageRepository.getById(dto.getEntityId());
        if (!validate(cottage, UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()), UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()))) {
            return -1L;
        } else {
            CottageReservation cottageReservation = new CottageReservation();
            cottageReservation.setCottage(cottage);
            cottageReservation.setReservationClient(clientRepository.getById(dto.getClientId()));
            cottageReservation.setReservationStart(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()));
            cottageReservation.setReservationEnd(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()));
            cottageReservation.setGuestNumber(dto.getGuestNumber());
            cottageReservation.setPrice(dto.getPrice());
            cottageReservation.setGuestNumber(dto.getGuestNumber());
            cottageReservation.setPrice(dto.getPrice());
            Set<CottageUtility> utilities = utilityService.convertStringToUtility(dto.getUtilities(), cottage);
            cottageReservation.setUtilities(utilities);
            mailingService.sendMailToUserAboutNewReservation(cottage.getCottageOwner(),clientRepository.getById(dto.getClientId()));
            return cottageReservationRepository.save(cottageReservation).getId();
        }
    }

    @Override
    public void deleteByAdmin(Long id) {
        Cottage cottage=cottageRepository.getById(id);
        cottage.setDeleted(true);
        cottageRepository.save(cottage);
    }

    private boolean validate(Cottage cottage, LocalDateTime startDate, LocalDateTime endDate) {
        List<CottageQuickReservation> quickReservations = cottageQuickReservationRepository.getOverlappedWithNewAction(startDate, endDate, cottage.getId());
        if (quickReservations.size() != 0) { //vec postoji kreiranja brza rezervacija u ovom periodu
            return false;
        }
        List<CottageReservation> cottageReservations = cottageReservationRepository.getOverlappedWithNewAction(startDate, endDate, cottage.getId());
        if (cottageReservations.size() != 0) {//vec postoji kreirana obicna rezervacija u ovom periodu
            return false;
        }
        List<AvailableCottageTimePeriod> periods = availableCottageTimePeriodRepository.getAvailabilityForDate(startDate, endDate, cottage.getId());
        if (periods.size() == 0) { //znaci da vikendica nije dostupna za vrijeme kreiranja
            return false;
        }

        return true;
    }

    private Set<Room> convertDtoToModel(List<RoomDto> rooms, Cottage cottage) {
        return rooms.stream().map(roomDto -> createRoom(roomDto, cottage)).collect(Collectors.toSet());
    }

    private Room createRoom(RoomDto roomDto, Cottage cottage) {
        if (roomDto.getId() == -1) {
            return new Room(roomDto.getNumberOfBeds(), cottage);
        } else {
            Room room = new Room();
            room.setId(roomDto.getId());
            room.setNumberOfBeds(roomDto.getNumberOfBeds());
            room.setCottage(cottage);
            return room;
        }
    }

    @Override
    public Boolean delete(Long id) {
        Cottage cottage = cottageRepository.getById(id);
        //TODO change condition of delete
        if ((cottage.getCottageReservations() == null || cottage.getCottageReservations().size() == 0)
                && (cottage.getCottageQuickReservations() == null || cottage.getCottageQuickReservations().size() == 0)) {
            cottage.setDeleted(true);
            cottageRepository.save(cottage);
            return true;
        }
        return false;
    }
}
