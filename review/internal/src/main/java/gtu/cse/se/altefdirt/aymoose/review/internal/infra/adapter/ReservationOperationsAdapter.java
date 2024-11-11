package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.Author;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReservationData;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.ReservationOperationsPort;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.UserOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Component
public class ReservationOperationsAdapter implements ReservationOperationsPort {
    
    // TODO: Implement getting the reservation data from the facility service
    @Override
    public ReservationData getReservationData(String reservationId, String userId) {
        return new ReservationData("41dc047c-de67-414e-aebe-bc1d42353434");
    }
}
