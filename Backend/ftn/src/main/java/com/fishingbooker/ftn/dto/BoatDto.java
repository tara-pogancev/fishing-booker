package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.AvailableTimePeriod;
import com.fishingbooker.ftn.bom.boats.BoatTypeEnum;
import lombok.Data;

import java.util.Set;

@Data
public class BoatDto {

    private Long id;
    private Double rating;
    private Double price;
    private String name;
    private BoatTypeEnum boatType;
    private Double boatLength;
    private Integer numberOfEngines;
    private Double enginePower;
    private Double maxSpeed;
    private Set<String> navigationalEquipments;
    private String address;
    private String description;
    private Integer guestLimit;
    private Set<String> rules;
    private String fishingEquipment;
    private Set<UtilityDto> utilities;
    private String ownerName;
    private Set<String> imageUrls;
    private Set<AvailableTimePeriod> availableTimePeriods;
    private Double cancellationPercentageKeep;

}
