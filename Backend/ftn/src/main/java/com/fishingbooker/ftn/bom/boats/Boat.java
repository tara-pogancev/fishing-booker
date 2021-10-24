package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
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

    @ManyToMany
    @JoinTable(
            name = "boat_navigational_equipment",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<NavigationalEquipment> navigationalEquipments;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "description", nullable = false)
    private String description;

    //todo slike

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

    //todo cenovnik i informacije o dodatnim uslugama

    @OneToMany(mappedBy="boat")
    private Set<AvailableBoatTimePeriod> availableTimePeriods;

    @Column(name = "cancellationPercentageKeep", nullable = false)
    private Double cancellationPercentageKeep;

    @ManyToOne
    @JoinColumn(name = "boatOwner")
    private BoatOwner boatOwner;

}
