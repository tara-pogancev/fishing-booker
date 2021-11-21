package com.fishingbooker.ftn.bom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AvailableTimePeriod extends DatabaseEntity {

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

}



