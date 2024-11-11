package gtu.cse.se.altefdirt.aymoose.review.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReservationData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ReservationOperationsPort {
    
    ReservationData getReservationData(String reservationId, String userId);
}
