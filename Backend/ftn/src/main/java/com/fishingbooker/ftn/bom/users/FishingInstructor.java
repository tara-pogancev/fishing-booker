package com.fishingbooker.ftn.bom.users;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "FishingInstructor")
public class FishingInstructor extends ApplicationUser {

    @Column(name = "biography")
    private String biography;

    //todo: period slobodnog vremena za obicne rezervacije

    @Column(name = "rating")
    private Double rating = 0.0;


}
