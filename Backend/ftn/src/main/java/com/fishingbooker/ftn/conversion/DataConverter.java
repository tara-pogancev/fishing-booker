package com.fishingbooker.ftn.conversion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
