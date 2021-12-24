package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ViewReservationDto {

    public Long id;
    public String entityType;
    public Long entityId;
    public String entityName;
    public String ownerName;
    public LocalDate startDate;
    public LocalDate endDate;
    public Double price;
    public Integer people;

}
