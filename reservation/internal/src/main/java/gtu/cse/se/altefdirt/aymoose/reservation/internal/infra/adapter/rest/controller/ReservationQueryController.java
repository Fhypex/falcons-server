package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.controller;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
@Slf4j
public class ReservationQueryController {

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    private static final class Parameter {
        private static final String COMPRESSED = "compressed";
        private static final String ID = "id";
        private static final String IN_USE = "inUse";
    }

    @GetMapping("/reservations/slots/{date}")
    public String getMethodName(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
    }

}
