package com.fishingbooker.ftn.bom.adventures;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.users.FishingInstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "AvailableInstructorTimePeriod")
@AllArgsConstructor
@RequiredArgsConstructor
public class AvailableInstructorTimePeriod extends AvailableTimePeriod {

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "instructor", nullable = false)
    @JsonBackReference
    private FishingInstructor instructor;

}