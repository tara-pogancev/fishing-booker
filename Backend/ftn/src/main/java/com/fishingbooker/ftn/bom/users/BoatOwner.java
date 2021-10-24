package com.fishingbooker.ftn.bom.users;

import com.fishingbooker.ftn.bom.boats.Boat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Table(name = "BoatOwner")
public class BoatOwner extends ApplicationUser {

    @OneToMany(mappedBy = "boatOwner")
    private Set<Boat> boats;

}
