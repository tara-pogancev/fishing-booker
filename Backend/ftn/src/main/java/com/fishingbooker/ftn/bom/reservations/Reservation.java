package com.fishingbooker.ftn.bom.reservations;

import com.fishingbooker.ftn.bom.DatabaseEntity;
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
    private Double price;

    @OneToOne
    @JoinColumn(name = "reservationClient", referencedColumnName = "id")
    private RegisteredUser reservationClient;

}
