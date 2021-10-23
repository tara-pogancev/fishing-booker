package com.fishingbooker.ftn.bom.users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "RegisteredUser")
public class RegisteredUser extends ApplicationUser {


}
