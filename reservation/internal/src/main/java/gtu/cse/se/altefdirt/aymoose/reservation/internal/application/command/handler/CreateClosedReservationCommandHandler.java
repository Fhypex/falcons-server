package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationFactory;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CreateClosedReservationCommandHandler
        implements CommandHandler<CreateClosedReservation, ClosedReservation> {

    private final ClosedReservationFactory factory;
    private final ClosedReservationRepository repository;
    private final ReservationService service;
    private final FacilityOperationPort facilityOperationPort;

    @Override
    public ClosedReservation handle(CreateClosedReservation command) {
        log.debug("Creating closed reservation {}", command);
        AggregateId courtId = AggregateId.fromUUID(command.courtId());
        if (service.isTimeSlotInUse(courtId, command.date(), command.hour())) {
            throw new RuntimeException("Time slot is in use");
        }
        WorkHours workHours = facilityOperationPort.getWorkHoursByCourtId(courtId);
        if (!workHours.isWithin(command.hour())) {
            throw new RuntimeException("Requested hour is not within work hours");
        }
        ClosedReservation reservation = factory.create(
                AggregateId.fromUUID(command.courtId()),
                command.date(),
                command.hour());
        return repository.save(reservation);
    }
}
