package com.fishingbooker.ftn.bom.users;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "RegisteredUser")
public class RegisteredUser extends ApplicationUser {

    @Column(name = "isBlocked", nullable = false)
    private Boolean isBlocked = false;

    @Column(name = "penalties", nullable = false)
    private Integer penalties = 0;

    //todo: ono za pretplate
    

}
