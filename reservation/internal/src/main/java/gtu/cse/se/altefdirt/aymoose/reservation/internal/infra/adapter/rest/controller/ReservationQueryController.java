package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.controller;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationRepository;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
@Slf4j
public class ReservationQueryController {

    private final ReservationRepository facilityRepository;
    private final ReservationService facilityService;

    private static final class Parameter {
        private static final String COMPRESSED = "compressed";
        private static final String ID = "id";
        private static final String IN_USE = "inUse";
    }
}
