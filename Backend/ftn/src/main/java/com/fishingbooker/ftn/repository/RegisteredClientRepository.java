package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredClientRepository extends EntityRepository<RegisteredClient> {
    @Query("SELECT c from RegisteredClient c WHERE c.enabled=true")
    public List<RegisteredClient> getEnabledClients();
}
