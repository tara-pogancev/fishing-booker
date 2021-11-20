package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.boats.NavigationalEquipment;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Adventure")
public class Adventure extends DatabaseEntity {

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

    // todo: photos
    // https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial

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
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<NavigationalEquipment> navigationalEquipments;

    @Column(name = "cancellationPercentageKeep", nullable = false)
    private Double cancellationPercentageKeep;

    @ManyToMany
    @JoinTable(
            name = "adventure_utilities",
            joinColumns = @JoinColumn(name = "adventure_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
    private Set<AdventureUtility> utilities;

    @ManyToOne
    @JoinColumn(name = "instructor")
    private FishingInstructor instructor;

}
