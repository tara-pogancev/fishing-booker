package com.fishingbooker.ftn.bom.cottages;

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
@Table(name = "AvailableCottageTimePeriod")
public class AvailableCottageTimePeriod extends AvailableTimePeriod {

    @ManyToOne
    @JoinColumn(name = "cottage", nullable = false)
    @JsonBackReference
    private Cottage cottage;

}
