package com.fishingbooker.ftn.bom.boats;


import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.cottages.Cottage;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AvailableBoatTimePeriod")
public class AvailableBoatTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name="boat", nullable=false)
    private Boat boat;

}