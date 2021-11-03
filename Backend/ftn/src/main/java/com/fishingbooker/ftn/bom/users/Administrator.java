package com.fishingbooker.ftn.bom.users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Administrator")
@PrimaryKeyJoinColumn(name = "userId")
public class Administrator extends ApplicationUser {

}
