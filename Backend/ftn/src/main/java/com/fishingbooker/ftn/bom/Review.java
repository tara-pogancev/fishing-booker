package com.fishingbooker.ftn.bom;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Review")
public class Review extends DatabaseEntity {

}
