package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.LocalDate;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface ClosedReservationRepository extends Repository<ClosedReservation, AggregateId> {

    List<ClosedReservation> findByCourtIdAndDate(AggregateId courtId, LocalDate date);
    boolean isTimeSlotInUse(AggregateId courtId, LocalDate date, int hour);
}