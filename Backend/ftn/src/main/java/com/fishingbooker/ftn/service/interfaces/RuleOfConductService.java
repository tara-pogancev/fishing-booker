package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.dto.RuleDto;

import java.util.List;

public interface RuleOfConductService {

    List<RuleOfConduct> createRulesFromString(List<RuleDto> rules);
}
