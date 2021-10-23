package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Boat")
public class Boat extends DatabaseEntity {

    private String name;
    private BoatType boatType;
    private Double boatLength;
    private Integer numberOfEngines;
    private Double enginePower;
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

    private Integer guestLimit;

    @OneToMany(mappedBy = "boat")
    private Set<BoatQuickReservation> boatQuickReservations;

    @ManyToMany
    @JoinTable(
            name = "boat_rules",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<RuleOfConduct> rules;

    private String fishingEquipment;

    //todo cenovnik i informacije o dodatnim uslugama

    //todo rename u nesto lepse
    private Double cancellationPercentageKeep;

    @ManyToOne
    @JoinColumn(name = "boatOwner")
    private BoatOwner boatOwner;

}
