package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.reservations.QuickReservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "AdventureQuickReservation")
@PrimaryKeyJoinColumn(name = "id")
public class AdventureQuickReservation extends QuickReservation {

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RegisteredClient reservationClient;

    @ManyToMany
    @JoinTable(
            name = "adventure_quick_reservation_utilities",
            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
    private Set<QuickReservationUtility> utilities;

}
