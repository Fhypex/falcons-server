package gtu.cse.se.altefdirt.aymoose.review.internal.domain;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface ReviewRepository extends Repository<Review, AggregateId> {

    List<Review> findByFacilityId(AggregateId facilityId);

    List<Review> findByUserIdAndFacilityId(AggregateId userId, AggregateId facilityId);

    List<Review> findByUserIdFacilityIdAndRatingEqual(AggregateId userId, AggregateId facilityId, Rating rating);

    List<Review> findByUserIdFacilityIdAndRatingLesserThan(AggregateId userId, AggregateId facilityId, Rating rating);

    List<Review> findByUserIdFacilityIdAndRatingGreaterThan(AggregateId userId, AggregateId facilityId, Rating rating);

    List<Review> findByUserId(AggregateId userId);
}
