package gtu.cse.se.altefdirt.aymoose.review.internal.application.repository;


import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.Query;

import java.util.List;

@Query
public interface FindByUserIdAndFacilityId {

    List<Review> query(String userId, String facilityId);
}