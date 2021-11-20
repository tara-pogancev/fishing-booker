package com.fishingbooker.ftn.dto;

import com.fishingbooker.ftn.bom.Rank;
import lombok.Data;

@Data
public class AdminViewUserDto {

    private Long id;
    private String name;
    private String lastName;
    private String mail;
    private String number;
    private Rank rank;
}
