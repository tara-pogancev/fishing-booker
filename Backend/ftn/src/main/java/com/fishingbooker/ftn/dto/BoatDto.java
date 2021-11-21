package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.boats.BoatType;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;
import java.util.Set;

@Data
public class BoatDto {

    private Long id;
    private Double rating;
    private Double price;
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
    private String ownerName;
    private Set<Long> imageIds;

}
