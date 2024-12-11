package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CompleteReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CompleteReservationCommandHandler implements CommandHandler<CompleteReservation, AggregateId> {

    private final ReservationRepository repository;

    @Override
    public AggregateId handle(CompleteReservation command) {
        log.debug("Completing reservation {}", command);
        AggregateId reservationId = AggregateId.fromUUID(command.id());
        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.complete();
        log.debug("Reservation completed {}", reservation);
        log.debug("Saving reservation", reservation);
        Reservation savedReservation = repository.save(reservation);
        return savedReservation.id();
    }
}
