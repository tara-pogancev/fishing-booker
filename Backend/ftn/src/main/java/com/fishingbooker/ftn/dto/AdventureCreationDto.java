package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdventureCreationDto {

    private Long id;
    private String name;
    private String description;
    private String street;
    private String city;
    private String country;
    private Integer guestLimit;
    private Double price;
    private Double cancellationFee;
    private List<String> rules;
    private List<UtilityDto> additionalServices;
    private List<String> fishingEquipment;
    private Long ownerId;
    private List<ImageDto> images;
}
