package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.impl;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.ReservationView;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service.ReservationService;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ReservationServiceImpl implements ReservationService {

    @Override
    public ReservationView denormalize(Reservation reservation) {

        return null;
    }
}
