package com.fishingbooker.ftn.bom.reservations;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

@Data
@Getter
@Setter
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

    @Column(name = "isReserved", nullable = false)
    private Boolean isReserved = false;

}
