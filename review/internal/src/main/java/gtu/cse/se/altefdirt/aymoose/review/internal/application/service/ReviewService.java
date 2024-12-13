package gtu.cse.se.altefdirt.aymoose.review.internal.application.service;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ReviewService {

    boolean isReviewExist(AggregateId reservationId, AggregateId userId);

    ReviewView denormalize(Review review);

    List<ReviewView> denormalize(List<Review> reviews);

    List<ReviewView> denormalizeForSameUser(List<Review> reviews);

    List<ReviewView> denormalizeForSameFacility(List<Review> reviews);

    List<ReviewView> denormalizeForSameFacilityAndUser(List<Review> reviews);
}
