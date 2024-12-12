package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservable;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.TimeSlotStatus;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateSlot {

    private final Date date;

    @JsonProperty("time_slots")
    private final TimeSlotStatus[] timeSlotStatuses = new TimeSlotStatus[24];

    public DateSlot(Date date, List<Reservable> reservations, WorkHours workHours) {
        this.date = date;
        init(reservations, workHours);
    }

    private void init(List<Reservable> reservations, WorkHours workHours) {
        for (int i = 0; i < workHours.openTime(); i++) {
            timeSlotStatuses[i] = TimeSlotStatus.CLOSED;
        }
        for (int i = workHours.openTime(); i < workHours.closeTime(); i++) {
            timeSlotStatuses[i] = TimeSlotStatus.AVAILABLE;
        }
        for (int i = workHours.closeTime(); i < 24; i++) {
            timeSlotStatuses[i] = TimeSlotStatus.CLOSED;
        }
        for (Reservable reservation : reservations) {
            timeSlotStatuses[reservation.getHour()] = TimeSlotStatus.RESERVED;
        }
    }

    public Date date() {
        return date;
    }
}
