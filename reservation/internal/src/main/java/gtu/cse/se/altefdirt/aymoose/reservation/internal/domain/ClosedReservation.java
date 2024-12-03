package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import lombok.Getter;
import java.time.LocalDate;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;

@Getter
public class ClosedReservation extends BaseAggregateRoot {

    private AggregateId courtId;
    private LocalDate date;
    private int hour;

    public ClosedReservation(AggregateId id,
            AggregateId courtId,
            LocalDate date,
            int hour) {
        super(id);
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }

    public AggregateId courtId() {
        return this.courtId;
    }

    public LocalDate date() {
        return this.date;
    }

    public int hour() {
        return this.hour;
    }
}
