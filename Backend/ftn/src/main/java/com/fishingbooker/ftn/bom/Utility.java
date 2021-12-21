package com.fishingbooker.ftn.bom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Utility")
@AllArgsConstructor
@RequiredArgsConstructor
public class Utility extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
