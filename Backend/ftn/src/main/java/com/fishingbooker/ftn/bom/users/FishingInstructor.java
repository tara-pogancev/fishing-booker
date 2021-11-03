package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.RegistrationRequest;
import com.fishingbooker.ftn.bom.UserRank;
import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "FishingInstructor")
@PrimaryKeyJoinColumn(name = "userId")
public class FishingInstructor extends ApplicationUser {

    @Column(name = "biography")
    private String biography;

    @Column(name = "rating")
    private Double rating = 0.0;

    @OneToMany(mappedBy = "instructor")
    private Set<AvailableInstructorTimePeriod> availableTimePeriods;

    @OneToOne(mappedBy = "fishingInstructor")
    private RegistrationRequest request;

    @ManyToOne()
    @JoinColumn(name="rank")
    UserRank rank;

}
