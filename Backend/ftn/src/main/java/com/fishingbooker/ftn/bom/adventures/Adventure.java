package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.boats.NavigationalEquipment;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Adventure")
public class Adventure extends DatabaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    @Column(name = "description", nullable = false)
    private String description;

    //todo: slike

    @Column(name = "guestLimit", nullable = false)
    private Integer guestLimit;

    @OneToMany(mappedBy = "adventure")
    private Set<AdventureQuickReservation> adventureQuickReservations;

    @ManyToMany
    @JoinTable(
            name = "adventure_rules",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<RuleOfConduct> rules;

    @ManyToMany
    @JoinTable(
            name = "adventure_navigational_equipment",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<NavigationalEquipment> navigationalEquipments;

    @Column(name = "cancellationPercentageKeep", nullable = false)
    private Double cancellationPercentageKeep;

    //todo cenovnik i informacije o dodatnim uslugama

}
