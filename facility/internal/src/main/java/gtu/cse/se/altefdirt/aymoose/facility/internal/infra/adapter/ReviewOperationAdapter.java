package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ReviewOperationPort;
import gtu.cse.se.altefdirt.aymoose.review.api.provider.ReviewProvider;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewOperationAdapter implements ReviewOperationPort {

    private final ReviewProvider reviewProvider;

    @Override
    public int getReviewCountByFacilityId(AggregateId id) {
        return reviewProvider.getReviewCountByFacilityId(id);
    }

    @Override
    public String getRatingByFacilityId(AggregateId id) {
        return reviewProvider.getAverageRatingByFacilityId(id);
    }
}
