package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.adventures.AvailableInstructorTimePeriod;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name = "FishingInstructor")
public class FishingInstructor extends ApplicationUser {

    @Column(name = "biography")
    private String biography;

    @Column(name = "rating")
    private Double rating = 0.0;

    @OneToMany(mappedBy = "instructor")
    private Set<AvailableInstructorTimePeriod> availableTimePeriods;

}
