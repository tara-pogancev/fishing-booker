package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class RejectRequestDto {
    private Long id;
    private String causeOfRejection;
}
