package com.fishingbooker.ftn.repository;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.repository.base.EntityRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface ApplicationUserRepository extends EntityRepository<ApplicationUser> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM ApplicationUser u")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "15000")})
    List<ApplicationUser> findAllLock();

}
