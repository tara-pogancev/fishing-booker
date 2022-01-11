package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class CreateAdventureReservationReportDto {

    private Long reservationId;
    private String comment;
    private boolean badComment;
    private boolean noClient;
}
