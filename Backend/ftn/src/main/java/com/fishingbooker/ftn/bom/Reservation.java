package com.fishingbooker.ftn.bom;

import com.fishingbooker.ftn.bom.users.RegisteredUser;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@MappedSuperclass
public class Reservation extends DatabaseEntity {

    @Column(name = "reservationStart", nullable = false)
    private LocalDate reservationStart;

    @Column(name = "reservationEnd", nullable = false)
    private LocalDate reservationEnd;

    @Column(name = "price", nullable = false)
    private float price;

    @OneToOne
    @JoinColumn(name = "reservationOwner", referencedColumnName = "id")
    private RegisteredUser reservationOwner;

}
