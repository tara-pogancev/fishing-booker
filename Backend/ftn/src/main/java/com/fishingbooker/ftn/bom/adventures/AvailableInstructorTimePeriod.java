package com.fishingbooker.ftn.bom.adventures;


import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.boats.Boat;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AvailableInstructorTimePeriod")
public class AvailableInstructorTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name="instructor", nullable=false)
    private FishingInstructor instructor;

}