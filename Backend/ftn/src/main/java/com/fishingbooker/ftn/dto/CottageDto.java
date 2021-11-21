package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CottageDto {

    private Long id;
    private Double rating;
    private Double price;
    private String name;
    private String address;
    private String description;
    private Integer guestLimit;
    private Set<String> rules;
    private Set<UtilityDto> utilities;
    private Set<RoomDto> rooms;
    private String ownerName;
    private String roomOverview;

}