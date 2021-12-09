package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InstructorPastReservationsDto {

    private Long reservationId;
    private String reservationType;
    private Long adventureId;
    private String adventureName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfPeople;
    private Double price;
    private Long clientId;
    private String clientName;
    private String clientLastName;
    private String clientMail;
    private String clientfullAddress;
    private String clientPhoneNumber;
}
