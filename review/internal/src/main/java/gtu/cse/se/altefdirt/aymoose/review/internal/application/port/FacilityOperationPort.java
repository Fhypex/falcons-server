package gtu.cse.se.altefdirt.aymoose.review.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface FacilityOperationPort {

    boolean isFacilityExist(AggregateId facilityId);
}
