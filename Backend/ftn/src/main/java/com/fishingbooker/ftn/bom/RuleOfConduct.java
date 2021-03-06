package com.fishingbooker.ftn.bom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "RulesOfConduct")
@RequiredArgsConstructor
@AllArgsConstructor
public class RuleOfConduct extends DatabaseEntity {

    @Column(name = "ruleDescription", nullable = false)
    private String ruleDescription;
}
