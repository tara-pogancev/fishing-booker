package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Image;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.boats.NavigationalEquipment;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Adventure")
public class Adventure extends DatabaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rating")
    private Double rating = 0.0;

    @Column(name = "price", nullable = false)
    private Double price = 0.0;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "adventure_images",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @Column(name = "guestLimit", nullable = false)
    private Integer guestLimit;

    @OneToMany(mappedBy = "adventure",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<AdventureQuickReservation> adventureQuickReservations;

    @OneToMany(mappedBy = "adventure",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<AdventureReservation> adventureReservations;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinTable(
            name = "adventure_rules",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<RuleOfConduct> rules;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "adventure_fishing_equipment",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<FishingEquipment> fishingEquipments;

    @Column(name = "cancellationPercentageKeep", nullable = false)
    private Double cancellationPercentageKeep;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "adventure_id")
    private Set<AdventureUtility> utilities;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "instructor")
    private FishingInstructor instructor;

}
