package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface LocalReservationRepository extends Repository<LocalReservation, AggregateId> {

    List<LocalReservation> findByCourtIdAndDate(AggregateId courtId, Date date);

    boolean isTimeSlotInUse(AggregateId courtId, Date date, int hour);
}
