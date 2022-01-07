package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewReservationDto {

    public Long id;
    public String entityType;
    public Long entityId;
    public String entityName;
    public String ownerName;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
    public Double price;
    public Integer people;

}
