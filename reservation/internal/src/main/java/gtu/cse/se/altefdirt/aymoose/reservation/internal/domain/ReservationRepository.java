package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface ReservationRepository extends Repository<Reservation, AggregateId> {

    List<Reservation> findByCourtIdAndDate(AggregateId courtId, Date date);

    int countByPendingReservationsByUserId(AggregateId userId);

    boolean isTimeSlotInUse(AggregateId courtId, Date date, int hour);

    List<Reservation> findByUserId(AggregateId userId);

    List<Reservation> findByOwnerId(AggregateId userId);

    List<Reservation> findByOwnerIdAndStatus(AggregateId courtId, ReservationStatus status);
}
