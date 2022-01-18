package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;
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
}
