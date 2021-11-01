package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "AdventureReservation")
public class AdventureReservation extends Reservation {

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    @ManyToMany
    @JoinTable(
            name = "adventure_reservation_utilities",
            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
    private Set<AdventureUtility> utilities;

}
