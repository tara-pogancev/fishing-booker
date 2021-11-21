package com.fishingbooker.ftn.bom.adventures;


import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "AvailableInstructorTimePeriod")
public class AvailableInstructorTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name = "instructor", nullable = false)
    private FishingInstructor instructor;

}