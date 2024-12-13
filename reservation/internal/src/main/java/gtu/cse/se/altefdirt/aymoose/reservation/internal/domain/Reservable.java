package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

public interface Reservable {

    AggregateId id();

    ReservationType getType();

    Date getDate();

    int getHour();
}
