package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreateLocalReservationRequestDTO(
        UUID courtId,
        String fullName,
        String phoneNumber,
        LocalDate date,
        int hour) {
    public CreateLocalReservationRequestDTO(
            UUID courtId,
            String fullName,
            String phoneNumber,
            LocalDate date,
            int hour) {
        this.courtId = courtId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.hour = hour;
    }
}
