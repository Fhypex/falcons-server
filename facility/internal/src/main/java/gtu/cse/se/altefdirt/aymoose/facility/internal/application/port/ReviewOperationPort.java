package gtu.cse.se.altefdirt.aymoose.facility.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ReviewOperationPort {

    int getReviewCountByFacilityId(AggregateId id);

    String getRatingByFacilityId(AggregateId id);

}
