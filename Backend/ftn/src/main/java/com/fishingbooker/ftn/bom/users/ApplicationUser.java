package com.fishingbooker.ftn.bom.users;


import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;

import javax.persistence.*;


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

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address userAddress;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role", nullable = false)
    private ApplicationRole role;

    @Column(name = "enabled")
    private Boolean enabled = false;

}
