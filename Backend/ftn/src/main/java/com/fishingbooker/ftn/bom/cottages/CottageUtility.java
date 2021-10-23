package com.fishingbooker.ftn.bom.cottages;


import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table (name = "CottageUtility")
public class CottageUtility extends DatabaseEntity {

    @Column(name = "utilityName", nullable = false)
    private String utilityName;

}
