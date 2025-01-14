package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.shared.application.FacilityData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

public interface FacilityOperationPort {

    boolean canReserve(AggregateId userId, AggregateId courtId, Date date, int timeSlot);

    WorkHours getWorkHoursByCourtId(AggregateId courtId);

    Optional<FacilityData> getByCourtId(AggregateId courtId);
}
