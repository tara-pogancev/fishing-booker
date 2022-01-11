package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends EntityRepository<Reservation> {


    @Query(value = "SELECT *, 0 AS clazz_ \n" +
            "\tFROM public.reservation WHERE reservation_end between :startDate and :endDate",nativeQuery = true)
    public List<Reservation> getReservationsInDate(@Param("startDate")LocalDate startDate,@Param("endDate") LocalDate endDate);

}
