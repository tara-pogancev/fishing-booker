package com.fishingbooker.ftn.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AdventureDto {

    private Long id;
    private String name;
    private String address;
    private String description;
    private Double rating;
    private Double price;
    private Integer guestLimit;
    private Set<String> rules;
    private Set<UtilityDto> utilities;
    private Set<String> navigationalEquipments;
    private String ownerName;
    private String instructorBiography;
    private Set<String> imageUrls;

}
