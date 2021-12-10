package com.fishingbooker.ftn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Long id;
    private Integer numberOfBeds;

}
