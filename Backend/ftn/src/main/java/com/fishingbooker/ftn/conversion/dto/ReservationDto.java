package com.fishingbooker.ftn.conversion.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ReservationDto {

    public Long id;
    public Long userId;
    public String entityType;
    public Long entityId;
    public LocalDate startDate;
    public LocalDate endDate;
    public Double price;
    public Set<Long> utilityIds;
    public Integer people;

}
