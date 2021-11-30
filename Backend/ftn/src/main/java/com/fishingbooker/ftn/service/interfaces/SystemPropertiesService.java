package com.fishingbooker.ftn.service.interfaces;

import com.fishingbooker.ftn.bom.SystemProperties;

public interface SystemPropertiesService {

    SystemProperties get();
    void updateComissionPrecentage(Double interestRate);
}
