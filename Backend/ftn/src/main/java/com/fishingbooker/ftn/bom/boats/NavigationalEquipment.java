package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Data
@Table (name = "NavigationalEquipment")
public class NavigationalEquipment extends DatabaseEntity {

    private NavigationalEquipmentEnum name;

}
