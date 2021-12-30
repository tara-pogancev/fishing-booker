package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ComplaintDto {

    public Long id;
    public Long entityId;
    public LocalDateTime date;
    public String description;
    public String approval;
    public String reservationType;
    public String reservationName;
    public LocalDateTime reservationStart;
    public LocalDateTime reservationEnd;
    public String userName;
    public Long userId;

}
