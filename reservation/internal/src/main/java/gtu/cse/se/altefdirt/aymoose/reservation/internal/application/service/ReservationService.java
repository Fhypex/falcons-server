package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlot;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.ReservationView;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

public interface ReservationService {

    ReservationView denormalize(Reservation reservation);

    boolean isTimeSlotInUse(AggregateId courtId, Date date, int hour);

    DateSlot getDateSlot(AggregateId courtId, WorkHours workHours, Date date);

    DateSlot getDateSlot(AggregateId courtId, Date date);

    List<DateSlot> getTimeSlotsOfBetweenDates(AggregateId courtId, WorkHours workHours, Date startDate,
            Date endDate);
}
