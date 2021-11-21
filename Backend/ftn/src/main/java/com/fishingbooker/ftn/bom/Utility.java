package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "Utility")
public class Utility extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
