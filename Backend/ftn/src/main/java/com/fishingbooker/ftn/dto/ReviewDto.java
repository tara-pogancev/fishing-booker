package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {

    public String review;
    public Long id;
    public Integer rating;
    public LocalDateTime date;
    public String approval;
    public String reservationName;
    public LocalDateTime reservationStart;
    public LocalDateTime reservationEnd;
    public String clientName;
    public String reservationType;
    public Long entityId;

}
