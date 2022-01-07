package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.EntityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ActionDto {

    private Long id;
    private Long entityId;
    private EntityType entityType;
    private String entityName;
    private Double rating;
    private Double price;
    private String ownerName;
    private Integer guestLimit;
    private Set<UtilityDto> utilities;
    private String address;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Set<String> imageUrls;

}
