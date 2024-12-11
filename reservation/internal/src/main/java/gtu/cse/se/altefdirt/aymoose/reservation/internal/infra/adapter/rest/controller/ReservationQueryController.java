package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.controller;

import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.JwtUser;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command.CreateReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
@Slf4j
public class ReservationQueryController {

    private final ReservationRepository reservationRepository;
    private final LocalReservationRepository localReservationRepository;
    private final ClosedReservationRepository closedReservationRepository;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String TYPE = "type";
    }

    @GetMapping("/reservations/slots/{date}")
    public String getMethodName(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return "Hello";
    }

    @GetMapping(value = "/reservations", params = Parameter.TYPE + "=default")
    public List<Reservation> getDefaultReservation() {
        return reservationRepository.findAll();
    }

    @GetMapping(value = "/reservations", params = Parameter.TYPE + "=local")
    public List<LocalReservation> getLocalReservation() {
        return localReservationRepository.findAll();
    }

    @GetMapping(value = "/reservations", params = Parameter.TYPE + "=closed")
    public List<ClosedReservation> getClosedReservation() {
        return closedReservationRepository.findAll();
    }

}
