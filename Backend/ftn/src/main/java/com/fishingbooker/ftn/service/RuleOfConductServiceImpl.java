package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.service.interfaces.RuleOfConductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RuleOfConductServiceImpl implements RuleOfConductService {
    @Override
    public List<RuleOfConduct> createRulesFromString(List<String> rules) {
        if (rules==null){
            return null;
        }
        List<RuleOfConduct> rulesOfConduct=rules.stream().map(rule-> createRuleObject(rule)).collect(Collectors.toList());
        return rulesOfConduct;
    }
    private RuleOfConduct createRuleObject(String rule){
        return new RuleOfConduct(rule);
    }
}
