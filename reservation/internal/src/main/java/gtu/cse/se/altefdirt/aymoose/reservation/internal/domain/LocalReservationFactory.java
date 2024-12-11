package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
public class LocalReservationFactory {

    public LocalReservation create(
            AggregateId courtId,
            String fullName,
            String phoneNumber,
            LocalDate date,
            int hour) {
        return new LocalReservation(AggregateId.generate(), courtId, fullName, phoneNumber, date, hour);
    }

    public LocalReservation load(
            AggregateId id,
            AggregateId courtId,
            String fullName,
            String phoneNumber,
            LocalDate date,
            int hour) {
        return new LocalReservation(AggregateId.generate(), courtId, fullName, phoneNumber, date, hour);
    }
}
