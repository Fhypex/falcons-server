package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByUserId;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.QueryImpl;
import lombok.RequiredArgsConstructor;

@QueryImpl
@RequiredArgsConstructor
class FindByUserIdImpl implements FindByUserId {

    private final JpaReviewRepository jpa;
    private final ReviewFactory reviewFactory;

    @Override
    public List<Review> query(String userId) {
        List<ReviewEntity> reviewEntities = jpa.findByUserId(userId);
        return reviewEntities.stream().map(entity -> entity.toDomain(reviewFactory)).collect(Collectors.toUnmodifiableList());

    }
}
