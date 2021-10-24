package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AvailableCottageTimePeriod")
public class AvailableCottageTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name="cottage", nullable=false)
    private Cottage cottage;

}
