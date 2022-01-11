package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class ReservationReportDto {

    private Long reportId;
    private String adventureName;
    private String clientName;
    private String ownerName;
    private String comment;
    private String reportType; //used to know in which table to search for report
}
