package com.fishingbooker.ftn.bom.reservations;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation extends DatabaseEntity {

    @Column(name = "reservationStart", nullable = false)
    private LocalDate reservationStart;

    @Column(name = "reservationEnd", nullable = false)
    private LocalDate reservationEnd;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToOne
    @JoinColumn(name = "reservationClient", referencedColumnName = "userId")
    private RegisteredClient reservationClient;

}
