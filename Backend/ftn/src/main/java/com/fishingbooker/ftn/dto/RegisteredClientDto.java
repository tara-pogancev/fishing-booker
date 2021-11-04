package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class RegisteredClientDto extends ApplicationUserDto {

    private Integer penalties;
    private Boolean isBlocked;

}
