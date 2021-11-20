package com.fishingbooker.ftn.bom.cottages;


import com.fishingbooker.ftn.bom.Utility;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Cottage")
public class CottageUtility extends Utility {

}
