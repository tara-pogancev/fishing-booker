package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "BoatUtility")
public class BoatUtility extends DatabaseEntity {

    @Column(name = "utilityName", nullable = false)
    private String utilityName;

}
