package gtu.cse.se.altefdirt.aymoose.review.internal.application.repository;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.Query;

@Query
public interface Save {
    Review save(Review review);
}
