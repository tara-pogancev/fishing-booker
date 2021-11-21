package com.fishingbooker.ftn.bom.cottages;


import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Utility;
import com.fishingbooker.ftn.bom.adventures.Adventure;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table (name = "CottageUtility")
public class CottageUtility extends DatabaseEntity {

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;

}
