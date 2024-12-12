package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

public record CreateClosedReservationRequestDTO(
        UUID courtId,
        Date date,
        Integer hour) {
    public CreateClosedReservationRequestDTO(
            UUID courtId,
            Date date,
            Integer hour) {
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }
}
