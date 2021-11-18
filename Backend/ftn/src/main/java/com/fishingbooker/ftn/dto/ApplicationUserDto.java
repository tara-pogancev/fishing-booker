package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class ApplicationUserDto {

    private Long id;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String fullAddress;
    private String street;
    private String city;
    private String country;
    private String phone;
    private String role;
    private Boolean enabled = false;
    private String registrationDescription;

}
