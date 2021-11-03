package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.bom.UserRank;
import com.fishingbooker.ftn.bom.boats.Boat;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "BoatOwner")
@PrimaryKeyJoinColumn(name = "userId")
public class BoatOwner extends ApplicationUser {

    @OneToMany(mappedBy = "boatOwner")
    private Set<Boat> boats;

    @OneToOne(mappedBy = "boatOwner")
    private RegistrationRequest request;

    @ManyToOne()
    @JoinColumn(name="rank")
    UserRank rank;

}
