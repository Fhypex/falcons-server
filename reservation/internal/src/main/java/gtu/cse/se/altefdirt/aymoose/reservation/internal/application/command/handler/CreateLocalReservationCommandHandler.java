package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateLocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationFactory;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CreateLocalReservationCommandHandler implements CommandHandler<CreateLocalReservation, LocalReservation> {

    private final LocalReservationFactory factory;
    private final LocalReservationRepository repository;
    private final FacilityOperationPort facilityOperationPort;

    @Override
    public LocalReservation handle(CreateLocalReservation command) {
        log.debug("Creating local reservation {}", command);
        AggregateId courtId = AggregateId.fromUUID(command.courtId());
        if (repository.isTimeSlotInUse(courtId, command.date(), command.hour())) {
            throw new RuntimeException("Time slot is in use");
        }
        WorkHours workHours = facilityOperationPort.getWorkHoursByCourtId(courtId);
        if (!workHours.isWithin(command.hour())) {
            throw new RuntimeException("Requested hour is not within work hours");
        }
        LocalReservation reservation = factory.create(
                AggregateId.fromUUID(command.courtId()),
                command.fullName(),
                command.phoneNumber(),
                command.date(),
                command.hour());
        return repository.save(reservation);
    }
}
