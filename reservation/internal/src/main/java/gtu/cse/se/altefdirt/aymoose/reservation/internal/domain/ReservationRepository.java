package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.LocalDate;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface ReservationRepository extends Repository<Reservation, AggregateId> {

    int countByPendingReservationsByUserId(AggregateId userId);

    boolean isTimeSlotInUse(AggregateId courtId, LocalDate date, int hour);
}
