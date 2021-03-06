package com.fishingbooker.ftn.bom.cottages;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fishingbooker.ftn.bom.adventures.QuickReservationUtility;
import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CottageQuickReservation")
@PrimaryKeyJoinColumn(name = "id")
public class CottageQuickReservation extends QuickReservation {

    @ManyToMany
    @JoinTable(
            name = "cottage_quick_reservation_utilities",
            joinColumns = @JoinColumn(name = "cottage_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
    private Set<QuickReservationUtility> utilities;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RegisteredClient reservationClient;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    @JsonBackReference
    private Cottage cottage;

    public void recalculateFullPrice() {
        double fullPrice = 0;
        if (this.utilities == null) {
            return;
        }
        for (QuickReservationUtility utility : this.utilities) {
            fullPrice += utility.getPrice();
        }
        this.setPrice(this.getPrice() + fullPrice);
    }
}
