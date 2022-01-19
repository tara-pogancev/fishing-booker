package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.boats.BoatTypeEnum;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class BoatCreationDto {
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
    private Long ownerId;
    private List<ImageDto> images;
    private BoatTypeEnum boatType;
    private Double boatLength;
    private Integer numberOfEngines;
    private Double enginePower;
    private Double maxSpeed;
    private Set<String> navigationalEquipments;
    private String fishingEquipment;
    private Double cancellationPercentageKeep;
}
