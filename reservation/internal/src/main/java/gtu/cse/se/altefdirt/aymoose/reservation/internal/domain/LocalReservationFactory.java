package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

@Component
public class LocalReservationFactory {

    public LocalReservation create(
            AggregateId courtId,
            String fullName,
            String phoneNumber,
            Date date,
            int hour) {
        return new LocalReservation(AggregateId.generate(), courtId, fullName, phoneNumber, date, hour);
    }

    public LocalReservation load(
            AggregateId courtId,
            String fullName,
            String phoneNumber,
            Date date,
            int hour) {
        return new LocalReservation(AggregateId.generate(), courtId, fullName, phoneNumber, date, hour);
    }
}
