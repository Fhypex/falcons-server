package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByUserIdAndFacilityIdAndRatingGreater;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Rating;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.QueryImpl;
import lombok.RequiredArgsConstructor;

@QueryImpl
@RequiredArgsConstructor
class FindByUserIdAndFacilityIdAndRatingGreaterImpl implements FindByUserIdAndFacilityIdAndRatingGreater {

    private final JpaReviewRepository jpa;
    private final ReviewFactory reviewFactory;

    @Override
    public List<Review> query(String userId, String facilityId, String rating) {
        Rating rounded = Rating.fromRound(rating, true);
        List<ReviewEntity> reviewEntities = jpa.findByUserIdAndFacilityIdAndRatingGreaterThan(userId, facilityId, rounded.value());
        return reviewEntities.stream().map(entity -> entity.toDomain(reviewFactory)).collect(Collectors.toUnmodifiableList());

    }
}
