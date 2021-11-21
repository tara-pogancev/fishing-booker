package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "BoatUtility")
public class BoatUtility extends DatabaseEntity {

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;

}
