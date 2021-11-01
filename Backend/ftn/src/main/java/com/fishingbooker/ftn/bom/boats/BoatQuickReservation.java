package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "BoatQuickReservation")
public class BoatQuickReservation extends QuickReservation {

    @ManyToMany
    @JoinTable(
            name = "boat_quick_reservation_utilities",
            joinColumns = @JoinColumn(name = "boat_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_utility_id"))
    private Set<BoatUtility> utilities;

    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;

}
