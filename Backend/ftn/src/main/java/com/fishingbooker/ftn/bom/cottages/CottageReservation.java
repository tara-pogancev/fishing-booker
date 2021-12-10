package com.fishingbooker.ftn.bom.cottages;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CottageReservation")
public class CottageReservation extends Reservation {

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    @JsonBackReference
    private Cottage cottage;

    @ManyToMany
    @JoinTable(
            name = "cottage_reservation_utilities",
            joinColumns = @JoinColumn(name = "cottage_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
    private Set<CottageUtility> utilities;

}
