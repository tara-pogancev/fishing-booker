package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.boats.*;
import com.fishingbooker.ftn.dto.*;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;
import java.util.List;

public interface BoatService {

    List<Boat> findAll();

    @Cacheable("boatDto")
    BoatDto findById(long id);

    Boolean delete(Long id);

    List<Boat> filterByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Boat> findFiltered(EntitySearchDto filterDto, Long userId);

    Boolean isBoatAvailable(Boat boat, LocalDateTime start, LocalDateTime end);

    Boat get(Long entityId);

    List<BoatReservation> getReservationsByBoat(Long boatId);

    List<BoatDto> findByBoatOwnerId(long id);

    Long create(BoatCreationDto boatDto);

    List<BoatType> getBoatTypes();

    List<NavigationalEquipment> getNavigationalEquipment();

    Long createQuickReservation(AdventureQuickReservationDto dto);

    List<BoatUtility> getBoatUtilities(Long id);

    Long createReservation(NewReservationDto dto);

    void deleteByAdmin(Long id);
}
