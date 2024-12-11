package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.LocalDate;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface ReservationRepository extends Repository<Reservation, AggregateId> {

    List<Reservation> findByCourtIdAndDate(AggregateId courtId, LocalDate date);

    int countByPendingReservationsByUserId(AggregateId userId);

    boolean isTimeSlotInUse(AggregateId courtId, LocalDate date, int hour);

}
