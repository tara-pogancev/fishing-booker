package com.fishingbooker.ftn.bom.users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "Administrator")
@PrimaryKeyJoinColumn(name = "userId")
public class Administrator extends ApplicationUser {

}
