package com.fishingbooker.ftn.bom.boats;


import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "AvailableBoatTimePeriod")
public class AvailableBoatTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name = "boat", nullable = false)
    private Boat boat;

}