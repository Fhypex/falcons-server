package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model;

import java.time.LocalDate;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservable;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.TimeSlotStatus;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

public class DateSlot {

    private final LocalDate date;
    private final TimeSlotsOfDay timeSlotsOfDay;

    public DateSlot(LocalDate date, TimeSlotsOfDay timeSlotsOfDay) {
        this.date = date;
        this.timeSlotsOfDay = timeSlotsOfDay;
    }

    public DateSlot(LocalDate date, List<Reservable> reservations, WorkHours workHours) {
        this.date = date;
        this.timeSlotsOfDay = new TimeSlotsOfDay(reservations, workHours);
    }

    public LocalDate date() {
        return date;
    }

    public TimeSlotsOfDay timeSlotsOfDay() {
        return timeSlotsOfDay;
    }
}

class TimeSlotsOfDay {

    private final TimeSlotStatus[] timeSlotStatuses = new TimeSlotStatus[24];

    public TimeSlotsOfDay(List<Reservable> reservations, WorkHours workHours) {
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

}
