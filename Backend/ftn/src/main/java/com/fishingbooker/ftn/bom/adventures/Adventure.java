package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.Address;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Image;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Adventure")
public class Adventure extends DatabaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    @OneToMany(mappedBy = "adventure", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private Set<AdventureQuickReservation> adventureQuickReservations;

    @OneToMany(mappedBy = "adventure", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private Set<AdventureReservation> adventureReservations;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adventure_id")
    private Set<AdventureUtility> utilities;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "instructor")
    private FishingInstructor instructor;

    @Column(name = "deleted")
    private boolean deleted = false;

    public List<AdventureReservation> getUnCanceledReservations() {
        List<AdventureReservation> uncanceled = new ArrayList<>();
        for (AdventureReservation adventureReservation : this.adventureReservations) {
            if (adventureReservation.getIsCanceled() == false) {
                uncanceled.add(adventureReservation);
            }
        }

        return uncanceled;
    }

    public boolean existsReservations() {
        if (adventureReservations==null){
            return false;
        }
        for (AdventureReservation reservation : this.adventureReservations) {
            if (reservation.getReservationEnd().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    public boolean existsQuickReservations() {
        if (this.adventureQuickReservations==null){
            return false;
        }
        for (AdventureQuickReservation reservation : this.adventureQuickReservations) {
            if (reservation.getActionEnd().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }
}
