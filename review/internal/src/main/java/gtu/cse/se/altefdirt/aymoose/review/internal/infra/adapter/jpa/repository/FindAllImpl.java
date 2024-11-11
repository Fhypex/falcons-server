package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.repository;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindAll;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.QueryImpl;
import lombok.RequiredArgsConstructor;

@QueryImpl
@RequiredArgsConstructor
class FindAllImpl implements FindAll {

    private final JpaReviewRepository jpa;
    private final ReviewFactory reviewFactory;

    @Override
    public List<Review> query() {
        List<ReviewEntity> entities = jpa.findAll();
        return entities.stream().map(entity -> entity.toDomain(reviewFactory)).toList();
    }
    
}
