package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.List;

@Data
public class EditAdventureDto {
    private Long id;
    private String name;
    private String description;
    private String street;
    private String city;
    private String country;
    private Integer guestLimit;
    private Double price;
    private Double cancellationFee;
    private List<RuleDto> rules;
    private List<AdventureUtilityDto> additionalServices;
    private List<FishingEquipmentDto> fishingEquipment;
    private Long ownerId;
    private List<ImageDto> images;
}
