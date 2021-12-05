package com.fishingbooker.ftn.bom;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "Image")
@RequiredArgsConstructor
public class Image extends DatabaseEntity {
    String url;
}
