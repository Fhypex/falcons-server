package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.Instant;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

@Component
public class ReservationFactory {

    public Reservation create(AggregateId userId,
            AggregateId ownerId,
            AggregateId courtId,
            Date date,
            int hour,
            ReservationStatus status,
            Instant requestedAt,
            Instant updatedAt) {
        return new Reservation(AggregateId.generate(), userId, ownerId, courtId, date, hour, status, requestedAt,
                updatedAt);
    }

    public Reservation load(
            AggregateId id,
            AggregateId userId,
            AggregateId ownerId,
            AggregateId courtId,
            Date date,
            int hour,
            ReservationStatus status,
            Instant requestedAt,
            Instant updatedAt) {
        return new Reservation(id, userId, ownerId, courtId, date, hour, status, requestedAt, updatedAt);
    }
}
