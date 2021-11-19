package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.boats.BoatType;
import lombok.Data;

import java.util.Set;

@Data
public class BoatDto {

    private Long id;
    private String name;
    private BoatType boatType;
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
    private String boatOwnerName;

}
