package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import java.time.Instant;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationFactory;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationStatus;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CreateReservationCommandHandler implements CommandHandler<CreateReservation, Reservation> {

    private final ReservationFactory factory;
    private final ReservationRepository repository;
    private final ReservationService service;
    private final FacilityOperationPort facilityOperationPort;

    @Override
    public Reservation handle(CreateReservation command) {
        log.debug("Handling create reservation command for {}", command);
        AggregateId courtId = AggregateId.fromUUID(command.courtId());
        log.debug("Checking if user has more than 3 pending reservations");
        if (repository.countByPendingReservationsByUserId(command.userId()) > 3) {
            throw new RuntimeException("Cannot reserve more than at the same time");
        }
        log.debug("Checking if time slot is in use");
        if (service.isTimeSlotInUse(courtId, command.date(), command.hour())) {
            throw new RuntimeException("Time slot is in use");
        }
        log.debug("Checking if time slot is within work hours");
        WorkHours workHours = facilityOperationPort.getWorkHoursByCourtId(courtId);
        if (!workHours.isWithin(command.hour())) {
            throw new RuntimeException("Requested hour is not within work hours");
        }
        Reservation reservation = factory.create(
                command.userId(),
                AggregateId.fromUUID(command.courtId()),
                command.date(),
                command.hour(),
                ReservationStatus.PENDING,
                Instant.now(),
                Instant.now());
        log.debug("Creating reservation command passed the checks with id {}", reservation.id());
        Reservation savedReservation = repository.save(reservation);
        log.debug("Reservation saved {}", savedReservation);
        return savedReservation;
    }
}
