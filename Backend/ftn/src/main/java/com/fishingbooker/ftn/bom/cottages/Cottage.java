package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Address;
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

    //todo: photos

    @OneToMany(mappedBy = "cottage", cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToMany(mappedBy = "cottage")
    private Set<CottageReservation> cottageReservations;

    @ManyToMany
    @JoinTable(
            name = "cottage_rules",
            joinColumns = @JoinColumn(name = "cottage_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<RuleOfConduct> rules;

    //todo cenovnik i informacije o dodatnim uslugama

}
