package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EntitySearchDto {

    public String country;
    public Integer people;
    public LocalDate startDate;
    public LocalDate endDate;

}
