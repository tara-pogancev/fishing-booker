package com.fishingbooker.ftn.email.context;

import com.fishingbooker.ftn.bom.users.ApplicationUser;
import com.fishingbooker.ftn.dto.ReservationDto;

public class ClientReservationConfirmationEmailContext extends AbstractEmailContext {

    @Override
    public <T> void init(T context) {
        //we can do any common configuration setup here
        // like setting up some base URL and context
        ApplicationUser user = (ApplicationUser) context;
        put("firstName", user.getName());
        setTemplateLocation("emails/client-reservation-complete");
        setSubject("Reservation complete!");
        setFrom("no-reply@fishing-booker.com");
        setTo(user.getEmail());
    }

    public void setReservationData(final ReservationDto reservationData) {
        put("type", reservationData.entityType);
        put("price", reservationData.price);
        put("startDate", reservationData.startDate.toString());

    }

}
