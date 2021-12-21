package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "BoatReservation")
public class BoatReservation extends Reservation {

    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;

    @ManyToMany
    @JoinTable(
            name = "boat_quick_reservation_utilities",
            joinColumns = @JoinColumn(name = "boat_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_utility_id"))
    private Set<BoatUtility> utilities;

}
