package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntitySearchDto {

    public String country;
    public Integer people;
    public LocalDateTime startDate;
    public LocalDateTime endDate;

}
