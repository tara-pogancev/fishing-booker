package com.fishingbooker.ftn.bom.users;


import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.security.registration.RegistrationToken;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ApplicationUser extends DatabaseEntity {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName", nullable = false)
    private String name;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address userAddress;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "role", nullable = false)
    private ApplicationRole role;

    @Column(name = "enabled")
    private Boolean enabled = false;

}
