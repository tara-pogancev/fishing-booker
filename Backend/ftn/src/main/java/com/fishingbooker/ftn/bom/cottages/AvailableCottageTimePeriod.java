package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "AvailableCottageTimePeriod")
public class AvailableCottageTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name = "cottage", nullable = false)
    private Cottage cottage;

}
