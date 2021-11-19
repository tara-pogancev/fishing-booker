package com.fishingbooker.ftn.bom.cottages;


import com.fishingbooker.ftn.bom.DatabaseEntity;
import com.fishingbooker.ftn.bom.Utility;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@DiscriminatorValue("Cottage")
public class CottageUtility extends Utility {

}
