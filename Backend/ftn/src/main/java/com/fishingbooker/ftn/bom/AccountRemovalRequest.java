package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "AccountRemovalRequest")
public class AccountRemovalRequest extends DatabaseEntity {

}
