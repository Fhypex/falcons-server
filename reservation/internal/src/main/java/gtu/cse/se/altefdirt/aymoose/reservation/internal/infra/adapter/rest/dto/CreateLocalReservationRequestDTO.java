package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

public record CreateLocalReservationRequestDTO(
        UUID courtId,
        String fullName,
        String phoneNumber,
        Date date,
        Integer hour) {
    public CreateLocalReservationRequestDTO(
            UUID courtId,
            String fullName,
            String phoneNumber,
            Date date,
            Integer hour) {
        this.courtId = courtId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.hour = hour;
    }
}
