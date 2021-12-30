package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowReservationInCalendarDto {

    private Long reservationId;
    private String clientName;
    private String clientSurname;
    private LocalDateTime start;
    private LocalDateTime end;
    private Double price;
    private Integer guestNumber;
}
