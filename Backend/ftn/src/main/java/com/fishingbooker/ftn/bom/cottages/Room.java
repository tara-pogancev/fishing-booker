package com.fishingbooker.ftn.bom.cottages;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.*;

import javax.persistence.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Room")
public class Room extends DatabaseEntity {

    @Column(name = "numberOfBeds", nullable = false)
    private Integer numberOfBeds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cottageId")
    @JsonBackReference
    private Cottage cottage;

}
