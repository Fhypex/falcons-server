package gtu.cse.se.altefdirt.aymoose.facility.api.provider;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface FacilityProvider {

    boolean isOwner(AggregateId facilityId, AggregateId userId);

    int deleteById(AggregateId id);

    int deleteByOwnerId(AggregateId userId);
}