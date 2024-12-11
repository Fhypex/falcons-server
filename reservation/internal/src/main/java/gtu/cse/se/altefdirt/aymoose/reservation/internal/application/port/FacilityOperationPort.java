package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port;

import java.time.LocalDate;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

public interface FacilityOperationPort {

    boolean canReserve(AggregateId userId, AggregateId courtId, LocalDate date, int timeSlot);

    WorkHours getWorkHours(AggregateId courtId);
}
