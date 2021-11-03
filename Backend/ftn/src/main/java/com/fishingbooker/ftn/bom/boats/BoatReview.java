package com.fishingbooker.ftn.bom.boats;


import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.users.BoatOwner;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "BoatReview")
public class BoatReview extends Review {

    @ManyToOne
    @JoinColumn(name = "boat", nullable = false)
    private Boat boat;

    @ManyToOne
    @JoinColumn(name = "boatOwner", nullable = false)
    private BoatOwner boatOwner;

    @ManyToMany
    @JoinTable(
            name = "boat_review_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    private Set<RegisteredClient> reviewClient;

}
