package gtu.cse.se.altefdirt.aymoose.review.internal.application.repository;

import gtu.cse.se.altefdirt.aymoose.shared.readmodel.Query;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;

@Query
public interface FindByUserIdAndFacilityIdAndRatingLesser {

    List<Review> query(String userId, String facilityId, String rating);
}