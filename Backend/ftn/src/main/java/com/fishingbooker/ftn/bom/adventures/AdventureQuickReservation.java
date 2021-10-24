package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.QuickReservation;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "AdventureQuickReservation")
public class AdventureQuickReservation extends QuickReservation {

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    @ManyToMany
    @JoinTable(
            name = "adventure_quick_reservation_utility",
            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
    private Set<AdventureUtility> utilities;

}
