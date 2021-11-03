package com.fishingbooker.ftn.security.registration;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.users.ApplicationUser;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "RegistrationToken")
public class RegistrationToken extends DatabaseEntity {

    @Column(name = "token", unique = true)
    private String token;

    @CreationTimestamp
    @Column(name = "timestamp", updatable = false)
    private Timestamp timestamp;

    @Column(name = "expireAt", updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private ApplicationUser user;

    @Transient
    private Boolean isExpired = false;

    public boolean isExpired() {
        return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
    }

}
