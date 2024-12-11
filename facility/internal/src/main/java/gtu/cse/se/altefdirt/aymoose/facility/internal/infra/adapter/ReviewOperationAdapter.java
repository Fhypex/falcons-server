package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ReviewOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ReviewOperationAdapter implements ReviewOperationPort {

    @Override
    public int getReviewCountByFacilityId(AggregateId id) {
        return 0;
    }

    @Override
    public String getRatingByFacilityId(AggregateId id) {
        return null;
    }
}
