package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface CourtOperationPort {
    
    int deleteByFacilityId(AggregateId facilityId);

    List<CourtData> findByFacilityId(AggregateId facilityId);

    List<CourtRichData> findByFacilityIdRich(AggregateId facilityId);
}
