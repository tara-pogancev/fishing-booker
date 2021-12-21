package com.fishingbooker.ftn.bom.boats;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "AvailableBoatTimePeriod")
public class AvailableBoatTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name = "boat", nullable = false)
    @JsonBackReference
    private Boat boat;

}