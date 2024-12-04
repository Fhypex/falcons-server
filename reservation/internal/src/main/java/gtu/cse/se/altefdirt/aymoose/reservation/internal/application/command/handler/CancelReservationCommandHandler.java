package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CancelReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port.CourtOperationPort;
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
public class CancelReservationCommandHandler implements CommandHandler<CancelReservation, AggregateId> {

    private final ReservationRepository repository;

    @Override
    public AggregateId handle(CancelReservation command) {

        log.debug("Approving reservation {}", command);

        AggregateId reservationId = AggregateId.from(command.id());

        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.cancel();

        log.debug("Reservation approved {}", reservation);

        log.debug("Saving reservation", reservation);

        Reservation savedReservation = repository.save(reservation);

        // Delete images
        return savedReservation.id();
    }
}
