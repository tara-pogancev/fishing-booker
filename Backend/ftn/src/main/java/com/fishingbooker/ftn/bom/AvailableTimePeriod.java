package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AvailableTimePeriod extends DatabaseEntity {

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

}



