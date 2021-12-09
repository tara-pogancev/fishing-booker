package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.bom.UserRank;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.bom.adventures.AdventureReservation;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "RegisteredClient")
@PrimaryKeyJoinColumn(name = "userId")
public class RegisteredClient extends ApplicationUser {

    @Column(name = "isBlocked", nullable = false)
    private Boolean isBlocked = false;

    @Column(name = "penalties", nullable = false)
    private Integer penalties = 0;

    @ManyToMany
    @JoinTable(
            name = "user_boat_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_id"))
    private Set<Boat> boatSubscription;

    @ManyToMany
    @JoinTable(
            name = "user_cottage_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cottage_id"))
    private Set<Cottage> cottageSubscription;

    @ManyToMany
    @JoinTable(
            name = "user_instructor_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private Set<FishingInstructor> instructorSubscription;

    @OneToOne(mappedBy = "user")
    private RegistrationRequest request;

    @ManyToOne()
    @JoinColumn(name = "rank")
    UserRank rank;





}
