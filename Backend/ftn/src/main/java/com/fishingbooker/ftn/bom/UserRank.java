package com.fishingbooker.ftn.bom;

import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "userRank")
public class UserRank extends DatabaseEntity {

    @Column(name = "userRank", nullable = false)
    private Rank userRank;

    @Column(name = "necessaryPoints", nullable = false)
    private Integer necessaryPoints;

    @OneToMany(mappedBy = "rank")
    private Set<RegisteredClient> registeredClients;

    @OneToMany(mappedBy = "rank")
    private Set<BoatOwner> boatOwners;

    @OneToMany(mappedBy = "rank")
    private Set<CottageOwner> cottageOwners;

    @OneToMany(mappedBy = "rank")
    private Set<FishingInstructor> fishingInstructors;

}
