package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Image;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Cottage")
public class Cottage extends DatabaseEntity {

    //todo: vikendica moze da ima periode zauzetosti koje definise vlasnik

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rating")
    private Double rating = 0.0;

    @Column(name = "price", nullable = false)
    private Double price = 0.0;

    @Column(name = "guestLimit", nullable = false)
    private Integer guestLimit;

    @ManyToMany
    @JoinTable(
            name = "cottage_images",
            joinColumns = @JoinColumn(name = "cottage_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @OneToMany(mappedBy = "cottage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToMany(mappedBy = "cottage")
    private Set<CottageQuickReservation> cottageQuickReservations;

    @ManyToMany
    @JoinTable(
            name = "cottage_rules",
            joinColumns = @JoinColumn(name = "cottage_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<RuleOfConduct> rules;

    @OneToMany
    @JoinColumn(name = "cottage_id")
    private Set<CottageUtility> utilities;

    @OneToMany(mappedBy = "cottage")
    private Set<AvailableCottageTimePeriod> availableTimePeriods;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cottageOwner")
    private CottageOwner cottageOwner;

}
