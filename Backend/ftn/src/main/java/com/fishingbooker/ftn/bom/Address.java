package com.fishingbooker.ftn.bom;

import com.fishingbooker.ftn.bom.DatabaseEntity;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Address")
public class Address extends DatabaseEntity {

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "street", nullable = false)
    private String street;

}
