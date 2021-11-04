package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class ApplicationUserDto {

    private Long id;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String userAddress;
    private String phone;
    private String role;
    private Boolean enabled = false;

}
