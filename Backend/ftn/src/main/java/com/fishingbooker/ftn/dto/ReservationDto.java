package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ReservationDto {

    public Long id;
    public Long userId;
    public String entityType;
    public Long entityId;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public Double price;
    public Set<Long> utilityIds;
    public Integer people;
    public Boolean createdReport;

}
