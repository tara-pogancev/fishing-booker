package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Table(name = "NavigationalEquipment")
public class NavigationalEquipment extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private NavigationalEquipmentEnum name;

}
