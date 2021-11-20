package com.fishingbooker.ftn.bom.adventures;

import com.fishingbooker.ftn.bom.Utility;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Fishing")
public class AdventureUtility extends Utility {

}
