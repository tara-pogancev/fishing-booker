package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.dto.ComplaintDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ComplaintToDto implements Converter<Complaint, ComplaintDto> {

    @Override
    public ComplaintDto convert(Complaint source) {
        ComplaintDto dto = new ComplaintDto();
        dto.setId(source.getId());
        dto.setEntityId(source.getEntityId());
        dto.setDate(source.getDate());
        dto.setDescription(source.getDescription());
        dto.setApproval(source.getApproval().toString());
        dto.setReservationType(source.getReservationType().toString().toLowerCase(Locale.ROOT));
        dto.setReservationStart(source.getReservation().getReservationStart());
        dto.setReservationEnd(source.getReservation().getReservationEnd());
        dto.setUserName(source.getUser().getFullName());
        return dto;
    }

}
