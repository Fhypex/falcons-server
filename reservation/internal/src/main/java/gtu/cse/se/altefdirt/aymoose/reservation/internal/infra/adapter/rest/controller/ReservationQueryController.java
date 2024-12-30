package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.controller;

import gtu.cse.se.altefdirt.aymoose.core.infra.security.access.AccessFacilityOwner;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.access.AccessUser;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt.JwtUser;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlot;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.DateSlotRich;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservationRepository;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservable;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        private static final String STATUS = "status";
        private static final String RICH = "rich";
    }

    @GetMapping("/reservations/slots/{court}/{date}")
    public DateSlot getMethodName(@PathVariable UUID court, @PathVariable Date date) {
        return reservationService.getDateSlot(AggregateId.fromUUID(court), date);
    }

    @GetMapping(value = "/reservation/{type}/{id}", params = { Parameter.TYPE, Parameter.ID })
    public Optional<? extends Reservable> getReservation(@PathVariable String type, @PathVariable UUID id) {
        switch (type) {
            case "local":
                return localReservationRepository.findById(AggregateId.fromUUID(id));
            case "closed":
                return closedReservationRepository.findById(AggregateId.fromUUID(id));
            default:
                return reservationRepository.findById(AggregateId.fromUUID(id));
        }
    }

    @GetMapping(value = "/reservations/all")
    public List<? extends Reservable> getAllReservation() {
        List<Reservable> reservations = new ArrayList<>();
        reservations.addAll(reservationRepository.findAll());
        reservations.addAll(localReservationRepository.findAll());
        reservations.addAll(closedReservationRepository.findAll());
        return reservations;
    }

    @GetMapping("/reservations/slots-rich/{court}/{date}")
    public DateSlotRich getDateSlotRich(@PathVariable UUID court, @PathVariable Date date) {
        return reservationService.getDateSlotRich(AggregateId.fromUUID(court), date);
    }

    @GetMapping(value = "/reservations", params = Parameter.TYPE + "=local")
    public List<LocalReservation> getLocalReservation() {
        return localReservationRepository.findAll();
    }

    @GetMapping(value = "/reservations", params = Parameter.TYPE + "=closed")
    public List<ClosedReservation> getClosedReservation() {
        return closedReservationRepository.findAll();
    }

    @AccessUser
    @GetMapping(value = "/reservations", params = Parameter.TYPE + "=owner")
    public List<Reservation> getPendingOwnerReservations(@AuthenticationPrincipal JwtUser user) {
        return reservationRepository.findByOwnerId(user.id());
    }

    @AccessUser
    @GetMapping(value = "/reservations", params = { Parameter.TYPE + "=owner", Parameter.STATUS })
    public List<Reservation> getPendingOwnerReservations(@AuthenticationPrincipal JwtUser user,
            @RequestParam String status) {
        return reservationRepository.findByOwnerId(user.id());
    }

    @AccessUser
    @GetMapping(value = "/reservations")
    public List<Reservation> getUsersReservations(@AuthenticationPrincipal JwtUser user) {
        return reservationRepository.findByUserId(user.id());
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
