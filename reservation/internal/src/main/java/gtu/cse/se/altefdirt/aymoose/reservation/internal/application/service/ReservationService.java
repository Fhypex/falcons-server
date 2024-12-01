package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.ReservationView;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;

public interface ReservationService {
    
    ReservationView denormalize(Reservation reservation);
}
