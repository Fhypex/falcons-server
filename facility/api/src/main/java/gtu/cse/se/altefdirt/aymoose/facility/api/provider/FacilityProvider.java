package gtu.cse.se.altefdirt.aymoose.facility.api.provider;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.shared.application.FacilityData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

public interface FacilityProvider {

    boolean isOwner(AggregateId facilityId, AggregateId userId);

    WorkHours getWorkHours(AggregateId facilityId);

    WorkHours getWorkHoursByCourtId(AggregateId courtId);

    int deleteById(AggregateId id);

    int deleteByOwnerId(AggregateId userId);

    boolean existsById(AggregateId id);

    String getFacilityName(AggregateId facilityId);

    Optional<FacilityData> getFacilityByCourtId(AggregateId facilityId);
}
