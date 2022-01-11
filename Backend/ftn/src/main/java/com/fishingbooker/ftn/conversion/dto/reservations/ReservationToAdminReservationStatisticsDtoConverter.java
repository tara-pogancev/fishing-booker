package com.fishingbooker.ftn.conversion.dto.reservations;

import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.dto.AdminReservationStatisticsDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservationToAdminReservationStatisticsDtoConverter implements Converter<Reservation, AdminReservationStatisticsDto> {
    @Override
    public AdminReservationStatisticsDto convert(Reservation source) {
        AdminReservationStatisticsDto dto = new AdminReservationStatisticsDto();
        dto.setId(source.getId());
        dto.setStartDate(source.getReservationStart());
        dto.setEndDate(source.getReservationEnd());
        dto.setPrice(source.getPrice());
        return dto;

    }
}
