package com.fishingbooker.ftn.bom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
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
