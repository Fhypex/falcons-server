package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface ClosedReservationRepository extends Repository<ClosedReservation, AggregateId> {

    List<ClosedReservation> findByCourtIdAndDate(AggregateId courtId, Date date);

    boolean isTimeSlotInUse(AggregateId courtId, Date date, int hour);
}
