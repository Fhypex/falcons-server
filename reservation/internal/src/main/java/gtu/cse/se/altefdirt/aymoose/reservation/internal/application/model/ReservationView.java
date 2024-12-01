package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import lombok.Builder;


@Builder
public record ReservationView(
        String id,
        String userId,
        String courtId,
        String date,
        int hour,
        String status,
        String requestedAt,
        String updatedAt) {
    public ReservationView(Reservation reservation) {
        this(reservation.id().value(),
                reservation.userId().value(),
                reservation.courtId().value(),
                reservation.date().toString(),
                reservation.hour(),
                reservation.status().name(),
                reservation.requestedAt().toString(),
                reservation.updatedAt().toString());
    }
}
