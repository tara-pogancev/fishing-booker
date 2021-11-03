package com.fishingbooker.ftn.bom.adventures;


import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "AdventureReview")
public class AdventureReview extends Review {

    @ManyToOne
    @JoinColumn(name = "adventure", nullable = false)
    private Adventure adventure;

    @ManyToOne
    @JoinColumn(name = "instructor", nullable = false)
    private FishingInstructor instructor;

    @ManyToMany
    @JoinTable(
            name = "adventure_review_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id"))
    private Set<RegisteredClient> reviewClient;

}
