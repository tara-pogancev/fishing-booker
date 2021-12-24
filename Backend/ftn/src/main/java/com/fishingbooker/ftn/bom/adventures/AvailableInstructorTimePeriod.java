package com.fishingbooker.ftn.bom.adventures;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "AvailableInstructorTimePeriod")
public class AvailableInstructorTimePeriod extends AvailableTimePeriod {

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "instructor", nullable = false)
    @JsonBackReference
    private FishingInstructor instructor;

}