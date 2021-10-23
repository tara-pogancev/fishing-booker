package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.cottages.Cottage;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name = "CottageOwner")
public class CottageOwner extends ApplicationUser{

    @OneToMany (mappedBy = "cottageOwner")
    private Set<Cottage> cottages;

}
