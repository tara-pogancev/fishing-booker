package com.fishingbooker.ftn.bom;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends DatabaseEntity {

    @Column(name = "review")
    private String review;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved = false;

}
