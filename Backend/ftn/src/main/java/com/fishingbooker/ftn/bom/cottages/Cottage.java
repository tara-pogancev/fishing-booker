package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Cottage")
public class Cottage extends DatabaseEntity {

    //todo: vikendica moze da ima periode zauzetosti koje definise vlasnik

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "description", nullable = false)
    private String description;

    // todo: photos
    // https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToMany(mappedBy = "cottage")
    private Set<CottageQuickReservation> cottageQuickReservations;

    @ManyToMany
    @JoinTable(
            name = "cottage_rules",
            joinColumns = @JoinColumn(name = "cottage_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<RuleOfConduct> rules;

    @ManyToMany
    @JoinTable(
            name = "cottage_utilities",
            joinColumns = @JoinColumn(name = "cottage_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "cottage_utility_id"))
    private Set<CottageUtility> utilities;

    @OneToMany(mappedBy = "cottage")
    private Set<AvailableCottageTimePeriod> availableTimePeriods;

    @ManyToOne
    @JoinColumn(name = "cottageOwner")
    private CottageOwner cottageOwner;

    @Column(name = "rating")
    private Double rating = 0.0;

}
