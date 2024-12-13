package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservable;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.TimeSlotStatus;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class DateSlotRich {

    private final Date date;

    @JsonProperty("timeSlots")
    private final RichTimeSlot[] timeSlots = new RichTimeSlot[24];

    public DateSlotRich(Date date, List<Reservable> reservations) {
        this.date = date;
        init(reservations);
    }

    private void init(List<Reservable> reservations) {
        log.debug("Initializing DateSlotRich for date {} with reservations {}", date, reservations);

        for (int i = 0; i < timeSlots.length; i++) {
            timeSlots[i] = new RichTimeSlot(TimeSlotStatus.PAST_TIME, null);
        }
        for (Reservable reservation : reservations) {
            timeSlots[reservation.getHour()] = new RichTimeSlot(TimeSlotStatus.RESERVED, reservation);
        }
    }

    public Date date() {
        return date;
    }
}

@Getter
@AllArgsConstructor
@NoArgsConstructor
class RichTimeSlot {
    private TimeSlotStatus status;
    private Reservable reservable;
}