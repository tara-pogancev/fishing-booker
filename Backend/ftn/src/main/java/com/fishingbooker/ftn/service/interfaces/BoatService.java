package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatReservation;
import com.fishingbooker.ftn.dto.BoatDto;
import com.fishingbooker.ftn.dto.EntitySearchDto;

import java.time.LocalDateTime;
import java.util.List;

public interface BoatService {

    List<Boat> findAll();

    BoatDto findById(long id);

    Long delete(Long id);

    List<Boat> filterByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<Boat> findFiltered(EntitySearchDto filterDto, Long userId);

    Boat get(Long entityId);

    List<BoatReservation> getReservationsByBoat(Long boatId);

}
