package com.fishingbooker.ftn.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeleteAccountRequestDto {
    private Long id;
    private String name;
    private String lastName;
    private String mail;
    private String userType;
    private String description;
}
