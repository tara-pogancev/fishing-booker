package com.fishingbooker.ftn.service;

import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.dto.RuleDto;
import com.fishingbooker.ftn.repository.RuleOfConductRepository;
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

    private final RuleOfConductRepository ruleRepository;
    @Override
    public List<RuleOfConduct> createRulesFromString(List<RuleDto> rules) {
        if (rules==null){
            return null;
        }
        List<RuleOfConduct> rulesOfConduct=rules.stream().map(rule-> createRuleObject(rule)).collect(Collectors.toList());
        return rulesOfConduct;
    }
    private RuleOfConduct createRuleObject(RuleDto ruleDto){
        if (ruleDto.getId()==-1){
            RuleOfConduct ruleOfConduct=new RuleOfConduct(ruleDto.getRuleDescription());
            return ruleRepository.save(ruleOfConduct);
        }else{
            RuleOfConduct ruleOfConduct=new RuleOfConduct();
            ruleOfConduct.setId(ruleDto.getId());
            ruleOfConduct.setRuleDescription(ruleDto.getRuleDescription());
            ruleRepository.save(ruleOfConduct);
            return ruleOfConduct;
        }

    }
}
