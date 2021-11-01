package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.bom.UserRank;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "CottageOwner")
public class CottageOwner extends ApplicationUser {

    @OneToMany(mappedBy = "cottageOwner")
    private Set<Cottage> cottages;

    @OneToOne(mappedBy = "cottageOwner")
    private RegistrationRequest request;

    @ManyToOne()
    @JoinColumn(name="rank")
    UserRank rank;

}
