package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

@Component
public class ClosedReservationFactory {

    public ClosedReservation create(
            AggregateId courtId,
            Date date,
            int hour) {
        return new ClosedReservation(AggregateId.generate(), courtId, date, hour);
    }

    public ClosedReservation load(
            AggregateId id,
            AggregateId courtId,
            Date date,
            int hour) {
        return new ClosedReservation(id, courtId, date, hour);
    }
}
