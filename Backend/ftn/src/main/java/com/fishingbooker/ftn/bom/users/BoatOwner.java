package com.fishingbooker.ftn.bom.users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "BoatOwner")
public class BoatOwner extends ApplicationUser {



}
