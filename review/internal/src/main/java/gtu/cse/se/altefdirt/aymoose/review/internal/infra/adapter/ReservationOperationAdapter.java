package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.ReservationOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.application.ReservationData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ReservationOperationAdapter implements ReservationOperationsPort {

    // TODO: Implement getting the reservation data from the facility service
    @Override
    public ReservationData getReservationData(AggregateId reservationId, AggregateId userId) {
        return new ReservationData(AggregateId.fromString("41dc047c-de67-414e-aebe-bc1d42353434"));
    }
}
