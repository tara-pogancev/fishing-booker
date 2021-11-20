package com.fishingbooker.ftn.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {

    private Long id;
    private  String name;
    private String lastName;
    private   String mail;
    private  String  userType;
    private String  registrationDescription;

}
