package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Utility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "QuickReservationUtility")
public class QuickReservationUtility extends DatabaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @Column(name = "price", nullable = false)
    private Double price;

}
