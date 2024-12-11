package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.LocalDate;

public interface Reservable {
    
    LocalDate getDate();

    int getHour();
}
