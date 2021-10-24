package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "ReservationReport")
public class ReservationReport extends DatabaseEntity {

}
