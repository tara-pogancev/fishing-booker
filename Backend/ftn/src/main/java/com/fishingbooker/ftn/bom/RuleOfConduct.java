package com.fishingbooker.ftn.bom;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "RulesOfConduct")
public class RuleOfConduct extends DatabaseEntity {

    @Column(name = "ruleDescription", nullable = false)
    private String ruleDescription;

}
