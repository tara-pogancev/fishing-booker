package com.fishingbooker.ftn.bom;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class Reservation extends DatabaseEntity {

    @Column(name = "reservationStart", nullable = false)
    private LocalDate reservationStart;

    @Column(name = "reservationEnd", nullable = false)
    private LocalDate reservationEnd;

    @Column(name = "price", nullable = false)
    private float price;


}
