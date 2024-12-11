package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import java.time.Instant;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
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
public class CreateReservationCommandHandler implements CommandHandler<CreateReservation, AggregateId> {

    private final ReservationFactory factory;
    private final ReservationRepository repository;
    private final FacilityOperationPort facilityOperationPort;

    @Override
    public AggregateId handle(CreateReservation command) {
        log.debug("Creating reservation {}", command);
        AggregateId courtId = AggregateId.fromUUID(command.courtId());
        if (repository.countByPendingReservationsByUserId(command.userId()) > 3) {
            throw new RuntimeException("Cannot reserve more than at the same time");
        }
        if (repository.isTimeSlotInUse(courtId, command.date(), command.hour())) {
            throw new RuntimeException("Time slot is in use");
        }
        WorkHours workHours = facilityOperationPort.getWorkHours(courtId);
        if (!workHours.isWithin(command.hour())) {
            throw new RuntimeException("Invalid hour");
        }
        Reservation reservation = factory.create(
                command.userId(),
                AggregateId.fromUUID(command.courtId()),
                command.date(),
                command.hour(),
                ReservationStatus.PENDING,
                Instant.now(),
                Instant.now());
        Reservation savedReservation = repository.save(reservation);
        log.debug("Reservation saved {}", savedReservation);
        return savedReservation.id();
    }
}
