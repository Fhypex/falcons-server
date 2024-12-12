package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import java.time.LocalDate;

import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

public interface Reservable {

    Date getDate();

    int getHour();
}
