package com.fishingbooker.ftn.bom.reservations;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
