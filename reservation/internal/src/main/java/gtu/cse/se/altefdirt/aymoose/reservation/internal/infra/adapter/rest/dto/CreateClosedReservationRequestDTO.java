package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreateClosedReservationRequestDTO(
        UUID courtId,
        LocalDate date,
        int hour) {
    public CreateClosedReservationRequestDTO(
            UUID courtId,
            LocalDate date,
            int hour) {
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }
}
