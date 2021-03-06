package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.UserRank;
import com.fishingbooker.ftn.bom.cottages.Cottage;
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

    @OneToMany(mappedBy = "cottageOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cottage> cottages;

    @ManyToOne()
    @JoinColumn(name = "rank")
    UserRank rank;

}
