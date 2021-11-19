package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Room")
public class Room extends DatabaseEntity {

    @Column(name = "numberOfBeds", nullable = false)
    private Integer numberOfBeds;

    @OneToOne
    @JoinColumn(name = "cottageId", referencedColumnName = "id")
    private Cottage cottage;

}
