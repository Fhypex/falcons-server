package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import lombok.Getter;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

@Getter
public class LocalReservation extends BaseAggregateRoot implements Reservable {

    private static final ReservationType type = ReservationType.LOCAL;
    private AggregateId courtId;
    private String name;
    private String phoneNumber;
    private Date date;
    private int hour;

    public LocalReservation(AggregateId id,
            AggregateId courtId,
            String name,
            String phoneNumber,
            Date date,
            int hour) {
        super(id);
        this.courtId = courtId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.hour = hour;
    }

    public AggregateId courtId() {
        return this.courtId;
    }

    public String name() {
        return this.name;
    }

    public String phoneNumber() {
        return this.phoneNumber;
    }

    public Date date() {
        return this.date;
    }

    public int hour() {
        return this.hour;
    }

    @Override
    public ReservationType getType() {
        return type;
    }
}
