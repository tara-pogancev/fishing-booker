package com.fishingbooker.ftn.conversion;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class UnixTimeToLocalDateTimeConverter {

    public static LocalDateTime TimeStampToDate(Long timestamp){
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp*1000),
                        TimeZone.getDefault().toZoneId());

        return triggerTime;
    }

    public static LocalDateTime adjustDefaultTimeZone(LocalDateTime time) {
        return time.plusHours(1);
    }

}
