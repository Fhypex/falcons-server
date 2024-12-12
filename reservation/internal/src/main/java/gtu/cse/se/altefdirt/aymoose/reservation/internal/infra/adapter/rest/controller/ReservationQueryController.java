package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.controller;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlot;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ReservationQueryController {

    private final ReservationRepository reservationRepository;
    private final LocalReservationRepository localReservationRepository;
    private final ClosedReservationRepository closedReservationRepository;
    private final ReservationService reservationService;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String TYPE = "type";
    }

    @GetMapping("/reservations/slots/{court}/{date}")
    public DateSlot getMethodName(@PathVariable UUID court, @PathVariable Date date) {
        return reservationService.getDateSlot(AggregateId.fromUUID(court), date);
    }

    @GetMapping(value = "/reservations")
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

@Component
class DateConverter
        implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        return Date.fromString(source);
    }
}
