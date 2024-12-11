package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import lombok.Builder;

@Builder
public record ReservationView(
        UUID id,
        UUID userId,
        UUID courtId,
        String date,
        int hour,
        String status,
        String requestedAt,
        String updatedAt,
        String reserverName) {
    public ReservationView(Reservation reservation, FullName reserverName) {
        this(reservation.id().value(),
                reservation.userId().value(),
                reservation.courtId().value(),
                reservation.date().toString(),
                reservation.hour(),
                reservation.status().name(),
                reservation.requestedAt().toString(),
                reservation.updatedAt().toString(),
                reserverName.value());
    }
}
