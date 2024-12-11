package gtu.cse.se.altefdirt.aymoose.review.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.shared.application.ReservationData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ReservationOperationsPort {

    ReservationData getReservationData(AggregateId reservationId, AggregateId userId);
}
