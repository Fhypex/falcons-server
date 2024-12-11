package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.time.LocalDate;
import java.util.UUID;

import org.apache.commons.lang3.Validate;

public record CreateReservationRequestDTO(
        UUID userId,
        UUID courtId,
        LocalDate date,
        int hour) {
    public CreateReservationRequestDTO(
            UUID userId,
            UUID courtId,
            LocalDate date,
            int hour) {
        Validate.notNull(userId, "User ID cannot be null");

        this.userId = userId;
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }
}
