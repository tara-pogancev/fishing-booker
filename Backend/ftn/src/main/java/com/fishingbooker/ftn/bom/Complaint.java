package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Complaint")
public class Complaint extends DatabaseEntity {

}
