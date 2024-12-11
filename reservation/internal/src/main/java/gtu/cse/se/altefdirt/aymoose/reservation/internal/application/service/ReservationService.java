package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service;

import java.time.LocalDate;
import java.util.List;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlot;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.ReservationView;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

public interface ReservationService {

    ReservationView denormalize(Reservation reservation);

    boolean isTimeSlotInUse(AggregateId courtId, LocalDate date, int hour);

    DateSlot getDateSlot(AggregateId courtId, WorkHours workHours, LocalDate date);

    List<DateSlot> getTimeSlotsOfBetweenDates(AggregateId courtId, WorkHours workHours, LocalDate startDate,
            LocalDate endDate);
}
