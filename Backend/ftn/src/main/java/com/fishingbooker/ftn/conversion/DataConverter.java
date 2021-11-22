package com.fishingbooker.ftn.conversion;


import com.fishingbooker.ftn.bom.Image;
import com.fishingbooker.ftn.bom.RuleOfConduct;
import com.fishingbooker.ftn.bom.boats.NavigationalEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
public class DataConverter {

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    public <T, S> T convert(S source, Class<T> targetType) {
        T convert = conversionService.convert(source, targetType);
        return convert;
    }

    public <T> List<T> convert(List<?> source, Class<T> targetType) {
        List<T> convertedTypes = source.stream().map(s -> conversionService.convert(s, targetType)).collect(toList());
        return convertedTypes;
    }


    public static Set<String> getRules(Set<RuleOfConduct> rules) {
        Set<String> retVal = new HashSet<>();
        for (RuleOfConduct rule : rules) {
            retVal.add(rule.getRuleDescription());
        }
        return retVal;
    }

    public static Set<Long> getImageIds(Set<Image> images) {
        Set<Long> retVal = new HashSet<>();
        for (Image image : images) {
            retVal.add(image.getId());
        }
        return retVal;
    }


    public static Set<String> getNavEquipment(Set<NavigationalEquipment> equipments) {
        Set<String> retVal = new HashSet<>();
        for (NavigationalEquipment nav : equipments) {
            retVal.add(nav.getName().toString());
        }
        return retVal;
    }

}
