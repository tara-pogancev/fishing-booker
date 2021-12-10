package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.List;

@Data
public class CottageCreationDto {
    private Long id;
    private String name;
    private String description;
    private String street;
    private String city;
    private String country;
    private Integer guestLimit;
    private Double price;
    private List<RuleDto> rules;
    private List<UtilityDto> additionalServices;
    private List<RoomDto> rooms;
    private Long ownerId;
    private List<ImageDto> images;
}
