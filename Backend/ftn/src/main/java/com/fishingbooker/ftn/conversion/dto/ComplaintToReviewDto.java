package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.complaints.Complaint;
import com.fishingbooker.ftn.dto.ReviewDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ComplaintToReviewDto implements Converter<Complaint, ReviewDto> {

    @Override
    public ReviewDto convert(Complaint source) {
        ReviewDto dto = new ReviewDto();
        dto.setApproval(source.getApproval().toString());
        dto.setReservationStart(source.getReservation().getReservationStart());
        dto.setReservationEnd(source.getReservation().getReservationEnd());
        dto.setClientName(source.getUser().getFullName());
        dto.setDate(source.getDate());
        dto.setReview(source.getDescription());
        dto.setRating(null);
        dto.setId(source.getId());
        dto.setReservationType(source.getReservationType().toString().toLowerCase(Locale.ROOT));
        dto.setEntityId(source.getEntityId());
        return dto;
    }

}
