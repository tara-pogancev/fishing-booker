package com.fishingbooker.ftn.conversion.dto;

import com.fishingbooker.ftn.bom.Review;
import com.fishingbooker.ftn.dto.ReviewDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReviewToDto implements Converter<Review, ReviewDto> {

    @Override
    public ReviewDto convert(Review source) {
        ReviewDto dto = new ReviewDto();
        dto.setApproval(source.getApproval().toString());
        dto.setReservationStart(source.getReservation().getReservationStart());
        dto.setReservationEnd(source.getReservation().getReservationEnd());
        dto.setClientName(source.getClient().getFullName());
        dto.setDate(source.getDate());
        dto.setReview(source.getReview());
        dto.setRating(source.getRating());
        dto.setId(source.getId());
        dto.setReservationType(source.getReservationType());
        dto.setEntityId(source.getEntityId());
        return dto;
    }

}
