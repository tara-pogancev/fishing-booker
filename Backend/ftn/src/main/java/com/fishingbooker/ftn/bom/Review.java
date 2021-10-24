package com.fishingbooker.ftn.bom;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class Review extends DatabaseEntity {

    @Column(name = "review")
    private String review;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved = false;

}
