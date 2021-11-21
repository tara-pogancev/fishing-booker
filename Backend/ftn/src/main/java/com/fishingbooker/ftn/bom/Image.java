package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Image")
public class Image extends DatabaseEntity {

    @Lob
    byte[] content;

    String name;

}
