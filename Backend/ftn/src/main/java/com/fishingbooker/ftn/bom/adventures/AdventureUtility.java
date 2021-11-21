package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Utility;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "AdventureUtility")
public class AdventureUtility extends DatabaseEntity {

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

}
