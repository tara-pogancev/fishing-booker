package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.UserRank;
import com.fishingbooker.ftn.bom.boats.Boat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "BoatOwner")
@PrimaryKeyJoinColumn(name = "userId")
public class BoatOwner extends ApplicationUser {

    @OneToMany(mappedBy = "boatOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Boat> boats;


    @ManyToOne()
    @JoinColumn(name = "rank")
    UserRank rank;

}
