package com.fishingbooker.ftn.bom.reservations;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation extends DatabaseEntity {

    @Column(name = "reservationStart", nullable = false)
    private LocalDateTime reservationStart;

    @Column(name = "reservationEnd", nullable = false)
    private LocalDateTime reservationEnd;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "guestNumber", nullable = false)
    private Integer guestNumber;

}
