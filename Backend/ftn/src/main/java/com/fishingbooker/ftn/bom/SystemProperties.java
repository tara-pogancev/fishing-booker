package com.fishingbooker.ftn.bom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "SystemProperties")
public class SystemProperties extends DatabaseEntity {

    @Column(name = "incomePercentage", nullable = false)
    private Double incomePercentage;

    @Column(name = "reservationPoints", nullable = false)
    private Integer reservationPoints;

    @Column(name = "silverPoints", nullable = false)
    private Integer silverPoints;

    @Column(name = "GoldPoints", nullable = false)
    private Integer goldPoints;

}
