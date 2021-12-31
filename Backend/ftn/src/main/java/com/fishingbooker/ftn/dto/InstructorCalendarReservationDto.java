package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InstructorCalendarReservationDto {

    private String reservationType;
    private Long reservationId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
