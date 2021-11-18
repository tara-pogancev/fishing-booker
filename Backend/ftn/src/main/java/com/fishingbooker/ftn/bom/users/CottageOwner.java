package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.bom.UserRank;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CottageOwner")
@PrimaryKeyJoinColumn(name = "userId")
public class CottageOwner extends ApplicationUser {

    @OneToMany(mappedBy = "cottageOwner")
    private Set<Cottage> cottages;



    @ManyToOne()
    @JoinColumn(name = "rank")
    UserRank rank;

}
