package com.fishingbooker.ftn.bom.adventures;


import com.fishingbooker.ftn.bom.Reservation;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.boats.BoatUtility;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "AdventureQuickReservation")
public class AdventureQuickReservation extends Reservation  {

    @Column(name = "guestLimit", nullable = false)
    private int guestLimit;

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    @ManyToMany
    @JoinTable(
            name = "adventure_reservation_utility",
            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
    private Set<AdventureUtility> utilities;

}
