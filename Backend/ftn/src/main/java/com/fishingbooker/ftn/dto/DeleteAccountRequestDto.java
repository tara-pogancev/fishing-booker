package com.fishingbooker.ftn.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.annotation.sql.DataSourceDefinitions;

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
