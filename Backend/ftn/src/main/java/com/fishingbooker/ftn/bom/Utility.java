package com.fishingbooker.ftn.bom;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "Utility")
@AllArgsConstructor
@RequiredArgsConstructor
public class Utility extends DatabaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
