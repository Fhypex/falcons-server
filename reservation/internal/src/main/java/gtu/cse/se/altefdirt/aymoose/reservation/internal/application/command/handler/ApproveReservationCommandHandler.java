package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.ApproveReservation;
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
public class ApproveReservationCommandHandler implements CommandHandler<ApproveReservation, AggregateId> {

    private final ReservationRepository repository;

    @Override
    public AggregateId handle(ApproveReservation command) {

        log.debug("Approving reservation {}", command);

        AggregateId reservationId = AggregateId.fromUUID(command.id());

        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.approve();

        log.debug("Reservation approved {}", reservation);

        log.debug("Saving reservation", reservation);

        Reservation savedReservation = repository.save(reservation);

        // Delete images
        return savedReservation.id();
    }
}
