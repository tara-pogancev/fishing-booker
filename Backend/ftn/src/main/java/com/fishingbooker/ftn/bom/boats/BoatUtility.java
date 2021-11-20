package com.fishingbooker.ftn.bom.boats;

import com.fishingbooker.ftn.bom.Utility;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Boat")
public class BoatUtility extends Utility {

}
