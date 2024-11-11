package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.repository;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.Save;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.QueryImpl;
import lombok.RequiredArgsConstructor;

@QueryImpl
@RequiredArgsConstructor
class SaveImpl implements Save {

    private final JpaReviewRepository jpa;
    private final ReviewFactory reviewFactory;

    @Override
    public Review save(Review review) {
        ReviewEntity entity = ReviewEntity.fromDomain(review);
        entity = jpa.save(entity);
        return entity.toDomain(reviewFactory);
    }
    
}
