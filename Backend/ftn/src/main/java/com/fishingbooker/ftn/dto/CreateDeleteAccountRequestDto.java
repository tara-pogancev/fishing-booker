package com.fishingbooker.ftn.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateDeleteAccountRequestDto {

    private Long userId;
    private String description;
}
