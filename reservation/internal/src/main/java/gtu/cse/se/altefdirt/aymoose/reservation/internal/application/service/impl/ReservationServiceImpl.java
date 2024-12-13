package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlot;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlotRich;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.ReservationView;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservable;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final LocalReservationRepository localRepository;
    private final ClosedReservationRepository closedRepository;
    private final FacilityOperationPort facilityOperationPort;

    @Override
    public ReservationView denormalize(Reservation reservation) {

        return null;
    }

    @Override
    public boolean isTimeSlotInUse(AggregateId courtId, Date date, int hour) {
        return repository.isTimeSlotInUse(courtId, date, hour) ||
                localRepository.isTimeSlotInUse(courtId, date, hour) ||
                closedRepository.isTimeSlotInUse(courtId, date, hour);
    }

    @Override
    public DateSlot getDateSlot(AggregateId courtId, Date date) {
        if (date.isBeforeToday()) {
            return DateSlot.pastTimeOf(date);
        }
        WorkHours workHours = facilityOperationPort.getWorkHoursByCourtId(courtId);
        if (date.isToday()) {
            return checkSlotForToday(courtId, workHours, date);
        } else {
            return checkSlotForUpcomingDay(courtId, workHours, date);
        }
    }

    @Override
    public DateSlot getDateSlot(AggregateId courtId, WorkHours workHours, Date date) {
        if (date.isBeforeToday()) {
            return DateSlot.pastTimeOf(date);
        }
        if (date.isToday()) {
            return checkSlotForToday(courtId, workHours, date);
        } else {
            return checkSlotForUpcomingDay(courtId, workHours, date);
        }
    }

    @Override
    public DateSlotRich getDateSlotRich(AggregateId courtId, Date date) {
        log.debug("Getting date slot rich for court {} and date {}", courtId, date);
        return new DateSlotRich(date, getReservables(courtId, date));
    }

    @Override
    public List<DateSlot> getTimeSlotsOfBetweenDates(AggregateId courtId, Date startDate,
            Date endDate) {

        List<DateSlot> dateSlots = new ArrayList<>();
        WorkHours workHours = facilityOperationPort.getWorkHoursByCourtId(courtId);
        for (Date date = startDate; date.localValue().isBefore(endDate.localValue()); date = date.plusDays(1)) {
            dateSlots.add(getDateSlot(courtId, workHours, date));
        }
        return dateSlots;
    }

    private DateSlot checkSlotForUpcomingDay(AggregateId courtId, WorkHours workHours, Date date) {
        return new DateSlot(date, getReservables(courtId, date), workHours);
    }

    private DateSlot checkSlotForToday(AggregateId courtId, WorkHours workHours, Date date) {
        return new DateSlot(date, getReservables(courtId, date), workHours, Date.currentHour());
    }

    private List<Reservable> getReservables(AggregateId courtId, Date date) {
        List<Reservable> reservables = new ArrayList<>();
        log.debug("Getting reservables for court {} and date {} with res {}", courtId, date,
                repository.findByCourtIdAndDate(courtId, date));
        log.debug("All reservables {}", repository.findAll());
        reservables.addAll(repository.findByCourtIdAndDate(courtId, date));
        reservables.addAll(localRepository.findByCourtIdAndDate(courtId, date));
        reservables.addAll(closedRepository.findByCourtIdAndDate(courtId, date));
        return reservables;
    }
}
