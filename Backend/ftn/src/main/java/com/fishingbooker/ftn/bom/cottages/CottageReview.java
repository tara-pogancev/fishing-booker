package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.users.CottageOwner;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CottageReview")
public class CottageReview extends Review {

    @ManyToOne
    @JoinColumn(name = "cottage", nullable = false)
    private Cottage cottage;

    @ManyToOne
    @JoinColumn(name = "cottageOwner", nullable = false)
    private CottageOwner cottageOwner;

    @ManyToMany
    @JoinTable(
            name = "cottage_review_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    private Set<RegisteredClient> reviewClient;

}
