package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredClientRepository extends EntityRepository<RegisteredClient> {
    @Query("SELECT c from RegisteredClient c WHERE c.enabled=true")
    public List<RegisteredClient> getEnabledClients();

    @Query(value="SELECT user_id\n" +
            "\tFROM public.adventure_reservation natural join public.reservation\n" +
            "\tWHERE NOW() between reservation_start and reservation_end and :instructorId in (SELECT instructor from public.adventure) ",nativeQuery = true)
    List<Long> getUsersWithReservationInMoment(@Param("instructorId") Long instructorId);
}
