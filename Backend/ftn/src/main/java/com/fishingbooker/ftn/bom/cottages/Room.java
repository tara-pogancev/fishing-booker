package com.fishingbooker.ftn.bom.cottages;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Room")
public class Room extends DatabaseEntity {

    @Column(name = "numberOfBeds", nullable = false)
    private Integer numberOfBeds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottageId")
    private Cottage cottage;

}
