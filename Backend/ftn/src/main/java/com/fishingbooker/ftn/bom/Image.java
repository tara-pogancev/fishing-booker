package com.fishingbooker.ftn.bom;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Image")
public class Image extends DatabaseEntity {

    @Lob
    byte[] content;

    String name;

}
