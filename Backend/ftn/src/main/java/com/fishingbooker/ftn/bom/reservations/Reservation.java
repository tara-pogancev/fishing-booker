package com.fishingbooker.ftn.bom.reservations;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation extends DatabaseEntity {

    @Column(name = "reservationStart", nullable = false)
    private LocalDate reservationStart;

    @Column(name = "reservationEnd", nullable = false)
    private LocalDate reservationEnd;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "reservationClient", referencedColumnName = "userId")
    private RegisteredClient reservationClient;

    @Column(name="guestNumber",nullable = false)
    private Integer guestNumber;

}
