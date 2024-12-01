package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port;

import java.time.LocalDate;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

public interface FacilityOperationPort {
    
    boolean canReserve(AggregateId userId, AggregateId courtId, LocalDate date, int timeSlot);

    WorkHours getWorkHours(AggregateId courtId);

    List<CourtData> findByFacilityId(AggregateId facilityId);

    List<CourtRichData> findByFacilityIdRich(AggregateId facilityId);
}
