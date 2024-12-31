package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model;

import java.util.ArrayList;
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
    private final List<List<RichTimeSlot>> timeSlots = new ArrayList<>(24);

    public DateSlotRich(
            Date date, List<Reservable> reservations) {
        for (int i = 0; i < 24; i++) {
            timeSlots.add(new ArrayList<>());
        }
        this.date = date;
        init(reservations);
    }

    private void init(List<Reservable> reservations) {
        log.debug("Initializing DateSlotRich for date {} with reservations {}", date, reservations);

        for (int i = 0; i < timeSlots.size(); i++) {
            timeSlots.get(i).add(new RichTimeSlot(TimeSlotStatus.PAST_TIME, null));
        }
        for (Reservable reservation : reservations) {
            timeSlots.get(reservation.getHour()).add(new RichTimeSlot(TimeSlotStatus.RESERVED, reservation));
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