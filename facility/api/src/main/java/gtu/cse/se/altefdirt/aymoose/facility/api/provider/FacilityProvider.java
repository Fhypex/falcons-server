package gtu.cse.se.altefdirt.aymoose.facility.api.provider;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface FacilityProvider {

    int deleteById(AggregateId id);

    CourtData getFacilityById(AggregateId id);

    List<CourtData> getFacilitiesByOwnerId(AggregateId ownerId);

    int deleteByOwnerId(AggregateId ownerId);
}
