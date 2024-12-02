package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.time.LocalDate;
import org.apache.commons.lang3.Validate;

public record CreateReservationRequestDTO(
        String userId,
        String courtId,
        LocalDate date,
        int hour){
    public CreateReservationRequestDTO(
        String userId,
        String courtId,
        LocalDate date,
        int hour) {
        Validate.notNull(userId, "User ID cannot be null");

        Validate.isTrue(userId.length() == 36, "Invalid user ID");

        this.userId = userId;
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }
}
