package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class FishingInstructorDto extends ApplicationUserDto {

    private String biography;
    private Double rating;

}
