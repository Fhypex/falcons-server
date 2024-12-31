package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservable;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationStatus;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.TimeSlotStatus;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateSlot {

    private final Date date;

    @JsonProperty("timeSlots")
    private final TimeSlotStatus[] timeSlotStatuses = new TimeSlotStatus[24];

    public DateSlot(Date date, List<Reservable> reservations, WorkHours workHours) {
        this.date = date;
        init(reservations, workHours);
    }

    public DateSlot(Date date, List<Reservable> reservations, WorkHours workHours, int notBeforeHour) {
        this.date = date;
        init(reservations, workHours, notBeforeHour);
    }

    public static DateSlot pastTimeOf(Date date) {
        DateSlot dateSlot = new DateSlot(date, List.of(), WorkHours.of(0, 24));
        for (int i = 0; i < 24; i++) {
            dateSlot.timeSlotStatuses[i] = TimeSlotStatus.PAST_TIME;
        }
        return dateSlot;
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
            if (reservation instanceof Reservation) {
                Reservation r = (Reservation) reservation;
                if (r.getStatus().equals(ReservationStatus.CANCELLED)
                        || r.getStatus().equals(ReservationStatus.REJECTED)) {
                    continue;
                }
            }
            timeSlotStatuses[reservation.getHour()] = TimeSlotStatus.RESERVED;
        }
    }

    private void init(List<Reservable> reservations, WorkHours workHours, int notBeforeHour) {
        for (int i = 0; i < notBeforeHour; i++) {
            timeSlotStatuses[i] = TimeSlotStatus.PAST_TIME;
        }
        for (int i = notBeforeHour; i < workHours.openTime(); i++) {
            timeSlotStatuses[i] = TimeSlotStatus.CLOSED;
        }
        Integer startHour = Math.max(notBeforeHour, workHours.openTime());
        for (int i = startHour; i < workHours.closeTime(); i++) {
            timeSlotStatuses[i] = TimeSlotStatus.AVAILABLE;
        }
        for (int i = workHours.closeTime(); i < 24; i++) {
            timeSlotStatuses[i] = TimeSlotStatus.CLOSED;
        }
        for (Reservable reservation : reservations) {
            if (reservation instanceof Reservation) {
                Reservation r = (Reservation) reservation;
                if (r.getStatus().equals(ReservationStatus.CANCELLED)
                        || r.getStatus().equals(ReservationStatus.REJECTED)) {
                    continue;
                }
            }
            timeSlotStatuses[reservation.getHour()] = TimeSlotStatus.RESERVED;
        }
    }

    public Date date() {
        return date;
    }
}
