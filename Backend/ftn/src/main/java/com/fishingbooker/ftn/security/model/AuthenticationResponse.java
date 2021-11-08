package com.fishingbooker.ftn.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {

    private String jwt;
    private String name;
    private String role;
    private String email;

}