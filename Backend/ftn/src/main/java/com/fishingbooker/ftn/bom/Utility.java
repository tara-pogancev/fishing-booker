package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="utility_type",
        discriminatorType = DiscriminatorType.STRING)
public class Utility extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

}
