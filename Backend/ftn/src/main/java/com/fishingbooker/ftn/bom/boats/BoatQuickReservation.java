package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.Reservation;
import com.fishingbooker.ftn.bom.cottages.CottageUtility;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "BoatQuickReservation")
public class BoatQuickReservation extends Reservation {

    //todo ne sme biti veci od kapaciteta samog broda!
    @Column(name = "guestLimit", nullable = false)
    private int guestLimit;

    @ManyToMany
    @JoinTable(
            name = "boat_reservation_utility",
            joinColumns = @JoinColumn(name = "boat_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_utility_id"))
    private Set<BoatUtility> utilities;

    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;

}
