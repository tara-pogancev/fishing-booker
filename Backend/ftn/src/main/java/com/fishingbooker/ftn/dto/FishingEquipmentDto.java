package com.fishingbooker.ftn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FishingEquipmentDto {
    private Long id;
    private String fishingEquipmentName;
}
