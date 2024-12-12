package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.DeleteLocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class DeleteLocalReservationCommandHandler implements CommandHandler<DeleteLocalReservation, Integer> {

    private final LocalReservationRepository repository;

    @Override
    public Integer handle(DeleteLocalReservation command) {
        log.debug("Deleting local reservation {}", command);
        AggregateId reservationId = AggregateId.fromUUID(command.id());
        return repository.deleteById(reservationId);
    }
}
