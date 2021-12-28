package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReviewDto {

    public String review;
    public Long id;
    public Integer rating;
    public LocalDateTime date;
    public String approval;
    public String reservationName;
    public LocalDate reservationStart;
    public LocalDate reservationEnd;
    public String clientName;
    public String reservationType;
    public Long entityId;

}
