package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.util.UUID;
import org.apache.commons.lang3.Validate;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

public record CreateReservationRequestDTO(
        UUID userId,
        UUID courtId,
        Date date,
        Integer hour) {
    public CreateReservationRequestDTO(
            UUID userId,
            UUID courtId,
            Date date,
            Integer hour) {
        Validate.notNull(userId, "User ID cannot be null");

        this.userId = userId;
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }
}
