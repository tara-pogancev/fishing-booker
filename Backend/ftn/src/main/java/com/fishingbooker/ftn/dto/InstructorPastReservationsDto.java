package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InstructorPastReservationsDto {

    private Long reservationId;
    private String reservationType;
    private Long adventureId;
    private String adventureName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer numberOfPeople;
    private Double price;
    private Long clientId;
    private String clientName;
    private String clientLastName;
    private String clientMail;
    private String clientfullAddress;
    private String clientPhoneNumber;
}
