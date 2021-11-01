package com.fishingbooker.ftn.bom.reservations;

import com.fishingbooker.ftn.bom.reservations.Reservation;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class QuickReservation extends Reservation {

    @Column(name = "guestLimit", nullable = false)
    private Integer guestLimit;

    @Column(name = "actionStart", nullable = false)
    private LocalDate actionStart;

    @Column(name = "actionEnd", nullable = false)
    private LocalDate actionEnd;

    @Column(name = "discount", nullable = false)
    private Double discount = 0.0;

}
