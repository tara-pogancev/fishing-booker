package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.adventures.*;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.conversion.UnixTimeToLocalDateTimeConverter;
import com.fishingbooker.ftn.dto.*;
import com.fishingbooker.ftn.repository.*;
import com.fishingbooker.ftn.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;
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
public class AdventureServiceImpl implements AdventureService {

    private final DataConverter converter;
    private final DateService dateService;
    private final ImageService imageService;
    private final UtilityService utilityService;
    private final AdventureRepository adventureRepository;
    private final RuleOfConductService ruleOfConductService;
    private final RegisteredClientRepository clientRepository;
    private final FishingInstructorRepository instructorRepository;
    private final FishingInstructorService fishingInstructorService;
    private final AvailableInstructorTimeRepository instructorTimeRepository;
    private final AdventureReservationRepository adventureReservationRepository;
    private final AdventureQuickReservationRepository adventureQuickReservationRepository;
    private final SubscriptionService subscriptionService;
    private final MailingService mailingService;

    @Override
    public List<AdventureDto> findAll() {
        List<Adventure> adventures = adventureRepository.findAll();
        return converter.convert(adventures, AdventureDto.class);
    }

    @Override
    public AdventureDto findById(long id) {

        if (adventureRepository.exists(id)) {
            Adventure adventure = adventureRepository.getById(id);
            return converter.convert(adventure, AdventureDto.class);
        }
        return null;
    }

    @Override
    public Long create(AdventureCreationDto adventureDto) {
        Adventure adventure;
        if (adventureDto.getId() == -1) {
            adventure = new Adventure();
        } else {
            adventure = adventureRepository.getById(adventureDto.getId());
        }

        FishingInstructor instructor = instructorRepository.getById(adventureDto.getOwnerId());
        Address address = new Address();
        adventure.setName(adventureDto.getName());
        address.setCity(adventureDto.getCity());
        address.setCountry(adventureDto.getCountry());
        address.setStreet(adventureDto.getStreet());
        adventure.setAddress(address);
        adventure.setPrice(adventureDto.getPrice());
        adventure.setInstructor(instructor);
        List<RuleOfConduct> rules = ruleOfConductService.createRulesFromString(adventureDto.getRules());
        adventure.setRules(new HashSet<>(rules));
        adventure.setFishingEquipments(convertDtoToModel(adventureDto.getFishingEquipment()));
        adventure.setDescription(adventureDto.getDescription());
        adventure.setCancellationPercentageKeep(adventureDto.getCancellationFee());
        adventure.setGuestLimit(adventureDto.getGuestLimit());
        try {
            adventure.setImages(imageService.saveImages(adventureDto.getImages()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Adventure savedAdventure = adventureRepository.save(adventure);
        Set<AdventureUtility> utilities = utilityService.convertStringToUtility(adventureDto.getAdditionalServices(), savedAdventure);
        savedAdventure.setUtilities(utilities);
        return adventureRepository.save(savedAdventure).getId();
    }

    private Set<FishingEquipment> convertDtoToModel(List<FishingEquipmentDto> fishingEquipment) {
        return fishingEquipment.stream().map(fishingEquipmentDto -> createEquipment(fishingEquipmentDto)).collect(Collectors.toSet());

    }

    private FishingEquipment createEquipment(FishingEquipmentDto fishingEquipmentDto) {
        if (fishingEquipmentDto.getId() == -1) {
            return new FishingEquipment(fishingEquipmentDto.getFishingEquipmentName());
        } else {
            FishingEquipment equipment = new FishingEquipment();
            equipment.setId(fishingEquipmentDto.getId());
            equipment.setFishingEquipmentName(fishingEquipmentDto.getFishingEquipmentName());
            return equipment;
        }
    }

    @Override
    public List<Adventure> getInstructorAdventures(Long id) {
        return adventureRepository.getInstructorAdventures(id);
    }

    @Override
    public Adventure get(Long id) {
        return adventureRepository.getById(id);
    }

    @Override
    public Long save(AdventureCreationDto adventureDto) {
        return 1l;
    }

    @Override
    public boolean deleteAdventure(Long id) {

        Adventure adventure = adventureRepository.getById(id);
        if (adventure.existsReservations() == false && adventure.existsQuickReservations() == false) {
            adventure.setDeleted(true);
            adventureRepository.save(adventure);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Adventure> filterByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Adventure> adventures = new ArrayList<>();
        for (Adventure adventure : adventureRepository.findAll()) {

            if (dateService.doDatesOverlapWithInstructorPeriodSet(startDate, endDate, adventure.getInstructor().getAvailableTimePeriods())) {
                // Passed availability check

                boolean reservationOverlap = false;
                for (AdventureReservation reservation : fishingInstructorService.getInstructorReservations(adventure.getInstructor().getId())) {
                    if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), startDate, endDate)) {
                        reservationOverlap = true;
                        break;
                    }
                }

                if (!reservationOverlap)
                    adventures.add(adventure);
            }

        }
        return adventures;
    }

    @Override
    public List<Adventure> findFiltered(EntitySearchDto filterDto, Long userId) {
        filterDto.setEndDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.endDate));
        filterDto.setStartDate(UnixTimeToLocalDateTimeConverter.adjustDefaultTimeZone(filterDto.startDate));

        List<Adventure> adventures = new ArrayList<>();
        List<Adventure> formerlyCanceled = getFormerlyCanceledAdventuresByUserByDate(filterDto, userId);

        for (Adventure adventure : filterByDate(filterDto.startDate, filterDto.endDate)) {
            if (!isIdInList(formerlyCanceled, adventure.getId()) && adventure.getGuestLimit() >= filterDto.people && (adventure.getAddress().getCountry().equals(filterDto.country) || filterDto.country.equals("")))
                adventures.add(adventure);
        }

        return adventures;
    }

    private List<Adventure> getFormerlyCanceledAdventuresByUserByDate(EntitySearchDto filterDto, Long userId) {
        List<AdventureReservation> canceled = adventureReservationRepository.getClientCanceledAdventureReservations(userId);
        List<Adventure> formerlyCanceledAdventures = new ArrayList<>();
        for (AdventureReservation reservation : canceled) {
            if (dateService.doDatesMatchNearby(filterDto.startDate, filterDto.endDate, reservation.getReservationStart(), reservation.getReservationEnd())) {
                formerlyCanceledAdventures.add(reservation.getAdventure());
            }
        }
        return formerlyCanceledAdventures;
    }

    private boolean isIdInList(List<Adventure> list, Long id) {
        for (Adventure adventure : list) {
            if (adventure.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Long createQuickReservation(CreateAdventureQuickReservationDto dto) {
        try {
            Adventure adventure = adventureRepository.getAdventure(dto.getAdventureId());
            if (!validate(adventure.getInstructor().getId(), UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getActionStart()), UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getActionEnd()))) {
                return -1l;
            } else {
                AdventureQuickReservation adventureQuickReservation = new AdventureQuickReservation();
                adventureQuickReservation.setAdventure(adventure);
                adventureQuickReservation.setActionStart(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getActionStart()));
                adventureQuickReservation.setActionEnd(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getActionEnd()));
                adventureQuickReservation.setGuestLimit(dto.getGuestLimit());
                adventureQuickReservation.setPrice(dto.getPrice());
                adventureQuickReservation.setUtilities(utilityService.convertUtilityDtoToUtility(dto.getAdventureUtilityDtoList()));
                adventureQuickReservation.recalculateFullPrice();
                AdventureQuickReservation persistedReservation = adventureQuickReservationRepository.save(adventureQuickReservation);
                List<RegisteredClient> clients = subscriptionService.getInstructorSubscribers(persistedReservation.getAdventure().getInstructor().getId());
                for (RegisteredClient client : clients) {
                    mailingService.sendMailToSubscribedUsers(client, persistedReservation.getReservationClient().getFullName());
                }
                return persistedReservation.getId();
            }
        } catch (Exception e) {
            System.out.println("Locking exception");
            return -1l;
        }
    }


    public Long createReservation(InstructorNewReservationDto dto) {
        try {
            Adventure adventure = adventureRepository.getAdventure(dto.getAdventureId());

            if (!validate(adventure.getInstructor().getId(), UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()), UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()))) {
                return -1L;
            } else {
                AdventureReservation adventureReservation = new AdventureReservation();
                adventureReservation.setAdventure(adventure);
                adventureReservation.setReservationClient(clientRepository.getById(dto.getClientId()));
                adventureReservation.setReservationStart(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getStartDate()));
                adventureReservation.setReservationEnd(UnixTimeToLocalDateTimeConverter.TimeStampToDate(dto.getEndDate()));
                adventureReservation.setGuestNumber(dto.getGuestNumber());
                adventureReservation.setPrice(dto.getPrice());
                adventureReservation.setGuestNumber(dto.getGuestNumber());
                adventureReservation.setPrice(dto.getPrice());
                Set<AdventureUtility> utilities = utilityService.convertStringToUtility(dto.getUtilities(), adventure);
                adventureReservation.setUtilities(utilities);
                adventureReservation.setInstructorId(adventure.getInstructor().getId());
                Long id=adventureReservationRepository.save(adventureReservation).getId();
                mailingService.sendMailToUserAboutNewReservation(adventure.getInstructor(),clientRepository.getById(dto.getClientId()));
                return id;
            }
        } catch (PessimisticLockingFailureException e) {
            System.out.println("Locking exception");
            return -1l;
        }
    }

    @Override
    public List<AdventureUtility> getAdventureUtilities(Long id) {
        Adventure adventure = adventureRepository.getById(id);
        return new ArrayList<>(adventure.getUtilities());
    }

    @Override
    public Boolean isAdventureAvailable(Adventure adventure, LocalDateTime start, LocalDateTime end) {
        boolean reservationOverlap = false;
        if (dateService.doDatesOverlapWithInstructorPeriodSet(start, end, adventure.getInstructor().getAvailableTimePeriods())) {
            // Passed availability check
            for (AdventureReservation reservation : fishingInstructorService.getInstructorReservations(adventure.getInstructor().getId())) {
                if (dateService.doPeriodsOverlap(reservation.getReservationStart(), reservation.getReservationEnd(), start, end)) {
                    reservationOverlap = true;
                    break;
                }
            }
        }
        return !reservationOverlap;
    }

    private boolean validate(Long instructorId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Adventure> adventures = adventureRepository.getInstructorAdventures(instructorId);
        for (Adventure adventure : adventures) {
            List<AdventureQuickReservation> quickReservations = adventureQuickReservationRepository.getOverlappedWithNewAction(startDate, endDate, adventure.getId());
            if (quickReservations.size() != 0) { //vec postoji kreiranja brza rezervacija u ovom periodu
                return false;
            }
            List<AdventureReservation> adventureReservations = adventureReservationRepository.getOverlappedWithNewAction(startDate, endDate, adventure.getId());
            if (adventureReservations.size() != 0) {//vec postoji kreirana obicna rezervacija u ovom periodu
                return false;
            }
        }
        List<AvailableInstructorTimePeriod> periods = instructorTimeRepository.getAvailabilityForDate(startDate, endDate, instructorId);
        if (periods.size() == 0) { //znaci da instruktor nije dostupan za vrijeme kreiranja
            return false;
        }
        return true;

    }

    @Override
    public List<AdventureQuickReservation> getQuickReservations(Long id) {
        Adventure adventure = adventureRepository.getById(id);
        if (adventure.getAdventureQuickReservations()==null){
            return new ArrayList<>();
        }else{
            List<AdventureQuickReservation> quickReservations = new ArrayList<>(adventure.getAdventureQuickReservations());
            return quickReservations;
        }

    }


}
