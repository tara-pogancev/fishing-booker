package com.fishingbooker.ftn.security.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {

    private String username;
    private String password;

}