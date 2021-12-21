package com.fishingbooker.ftn.bom;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Address")
public class Address extends DatabaseEntity {

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "street", nullable = false)
    private String street;

    @Override
    public String toString() {
        return street + ", " + city + ", " + country;
    }

}
