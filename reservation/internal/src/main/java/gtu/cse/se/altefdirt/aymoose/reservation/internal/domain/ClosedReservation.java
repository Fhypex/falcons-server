package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import lombok.Getter;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

@Getter
public class ClosedReservation extends BaseAggregateRoot implements Reservable {

    private AggregateId courtId;
    private Date date;
    private int hour;

    public ClosedReservation(AggregateId id,
            AggregateId courtId,
            Date date,
            int hour) {
        super(id);
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }

    public AggregateId courtId() {
        return this.courtId;
    }

    public Date date() {
        return this.date;
    }

    public int hour() {
        return this.hour;
    }
}
