package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.RuleOfConduct;

import java.util.List;

public interface RuleOfConductService {

    List<RuleOfConduct> createRulesFromString(List<String> rules);
}
