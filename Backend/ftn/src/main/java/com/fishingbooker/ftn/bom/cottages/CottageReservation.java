package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.Reservation;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "CottageReservation")
public class CottageReservation extends Reservation {

    @Column(name = "guestLimit", nullable = false)
    private int guestLimit;

    @ManyToMany
    @JoinTable(
            name = "cottage_reservation_utility",
            joinColumns = @JoinColumn(name = "cottage_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
    private Set<CottageUtility> utilities;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

}
