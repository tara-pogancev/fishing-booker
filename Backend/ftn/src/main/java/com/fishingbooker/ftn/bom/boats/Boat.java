package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Image;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Boat")
public class Boat extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "boatType", nullable = false)
    private BoatType boatType;

    @Column(name = "boatLength", nullable = false)
    private Double boatLength;

    @Column(name = "numberOfEngines")
    private Integer numberOfEngines;

    @Column(name = "enginePower", nullable = false)
    private Double enginePower;

    @Column(name = "maxSpeed", nullable = false)
    private Double maxSpeed;

    @Column(name = "rating")
    private Double rating = 0.0;

    @Column(name = "price", nullable = false)
    private Double price = 0.0;

    @ManyToMany
    @JoinTable(
            name = "boat_navigational_equipment",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<NavigationalEquipment> navigationalEquipments;

    @OneToOne
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "boat_images",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images;

    @Column(name = "guestLimit", nullable = false)
    private Integer guestLimit;

    @OneToMany(mappedBy = "boat")
    private Set<BoatQuickReservation> boatQuickReservations;

    @ManyToMany
    @JoinTable(
            name = "boat_rules",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<RuleOfConduct> rules;

    @Column(name = "fishingEquipment", nullable = false)
    private String fishingEquipment;

    @OneToMany
    @JoinColumn(name = "boat_id")
    private Set<BoatUtility> utilities;

    @OneToMany(mappedBy = "boat")
    private Set<AvailableBoatTimePeriod> availableTimePeriods;

    @Column(name = "cancellationPercentageKeep", nullable = false)
    private Double cancellationPercentageKeep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boatOwner")
    private BoatOwner boatOwner;

}
