package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "AdventureReservation")
@PrimaryKeyJoinColumn(name = "id")
public class AdventureReservation extends Reservation {

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

    @Column(name = "instructor_id")
    private Long instructorId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private RegisteredClient reservationClient;

    @ManyToMany
    @JoinTable(
            name = "adventure_reservation_utilities",
            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
    private Set<AdventureUtility> utilities;


}
