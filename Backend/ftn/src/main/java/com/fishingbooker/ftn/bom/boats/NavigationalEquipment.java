package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "NavigationalEquipment")
public class NavigationalEquipment extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private NavigationalEquipmentEnum name;

}
