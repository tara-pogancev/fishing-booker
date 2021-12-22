package com.fishingbooker.ftn.bom.cottages;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CottageReservation")
public class CottageQuickReservation extends QuickReservation {

    @ManyToMany
    @JoinTable(
            name = "cottage_quick_reservation_utilities",
            joinColumns = @JoinColumn(name = "cottage_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
    private Set<CottageUtility> utilities;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private RegisteredClient reservationClient;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    @JsonBackReference
    private Cottage cottage;

}
