package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.Instant;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
public class ReservationFactory {

    public Reservation create(AggregateId userId,
            AggregateId courtId,
            LocalDate date,
            int hour,
            ReservationStatus status,
            Instant requestedAt,
            Instant updatedAt) {
        return new Reservation(AggregateId.generate(), userId, courtId, date, hour, status, requestedAt, updatedAt);
    }

    public Reservation load(
            AggregateId id,
            AggregateId userId,
            AggregateId courtId,
            LocalDate date,
            int hour,
            ReservationStatus status,
            Instant requestedAt,
            Instant updatedAt) {
        return new Reservation(id, userId, courtId, date, hour, status, requestedAt, updatedAt);
    }
}
