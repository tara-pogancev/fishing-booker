package com.fishingbooker.ftn.bom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "Utility")
public class Utility extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
