package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Utility;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "AdventureUtility")
@AllArgsConstructor
@RequiredArgsConstructor
public class AdventureUtility extends DatabaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "adventure_id")
    private Adventure adventure;

}
