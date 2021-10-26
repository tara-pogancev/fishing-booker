package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.QuickReservation;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "CottageReservation")
public class CottageQuickReservation extends QuickReservation {

    @ManyToMany
    @JoinTable(
            name = "cottage_quick_reservation_utilities",
            joinColumns = @JoinColumn(name = "cottage_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
    private Set<CottageUtility> utilities;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

}
