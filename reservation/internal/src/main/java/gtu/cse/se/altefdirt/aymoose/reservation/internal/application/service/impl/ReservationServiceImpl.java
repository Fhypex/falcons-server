package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlot;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.ReservationView;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservable;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
    public DateSlot getDateSlot(AggregateId courtId, WorkHours workHours, Date date) {
        List<Reservation> reservations = repository.findByCourtIdAndDate(courtId, date);
        List<LocalReservation> localReservations = localRepository.findByCourtIdAndDate(courtId, date);
        List<ClosedReservation> closedReservations = closedRepository.findByCourtIdAndDate(courtId, date);
        List<Reservable> reservables = new ArrayList<>();
        reservables.addAll(reservations);
        reservables.addAll(localReservations);
        reservables.addAll(closedReservations);
        return new DateSlot(date, reservables, workHours);
    }

    @Override
    public DateSlot getDateSlot(AggregateId courtId, Date date) {
        WorkHours workHours = facilityOperationPort.getWorkHoursByCourtId(courtId);
        List<Reservation> reservations = repository.findByCourtIdAndDate(courtId, date);
        List<LocalReservation> localReservations = localRepository.findByCourtIdAndDate(courtId, date);
        List<ClosedReservation> closedReservations = closedRepository.findByCourtIdAndDate(courtId, date);
        List<Reservable> reservables = new ArrayList<>();
        reservables.addAll(reservations);
        reservables.addAll(localReservations);
        reservables.addAll(closedReservations);
        return new DateSlot(date, reservables, workHours);
    }

    @Override
    public List<DateSlot> getTimeSlotsOfBetweenDates(AggregateId courtId, WorkHours workHours, Date startDate,
            Date endDate) {
        List<DateSlot> dateSlots = new ArrayList<>();
        for (Date date = startDate; date.localValue().isBefore(endDate.localValue()); date = date.plusDays(1)) {
            dateSlots.add(getDateSlot(courtId, workHours, date));
        }
        return dateSlots;
    }
}
