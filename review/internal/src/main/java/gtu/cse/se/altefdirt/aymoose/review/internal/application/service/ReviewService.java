package gtu.cse.se.altefdirt.aymoose.review.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ReviewService {
    
    boolean isReviewExist(String reservationId, String userId);

    ReviewView denormalize(Review review);
}
