package gtu.cse.se.altefdirt.aymoose.review.api.provider;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.ReviewData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ReviewProvider {

    String getAverageRatingByFacilityId(AggregateId facilityId);

    String getAverageRatingByUserId(AggregateId userId);

    int getReviewCountByFacilityId(AggregateId facilityId);

    int getReviewCountByUserId(AggregateId userId);

    List<ReviewData> getReviewsByFaciliyId(AggregateId facilityId);

    List<ReviewData> getReviewsByUserId(AggregateId userId);
}