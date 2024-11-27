package gtu.cse.se.altefdirt.aymoose.court.api.provider;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface CourtProvider {

    int deleteById(AggregateId id);

    CourtData getCourtById(AggregateId id);

    List<CourtData> getCourtsByFacilityId(AggregateId facilityId);

    int deleteByFacilityId(AggregateId facilityId);
}
