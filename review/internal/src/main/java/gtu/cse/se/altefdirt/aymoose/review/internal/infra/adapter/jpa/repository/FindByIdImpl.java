package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.repository;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindById;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.QueryImpl;
import lombok.RequiredArgsConstructor;

@QueryImpl
@RequiredArgsConstructor
class FindByIdImpl implements FindById {

    private final JpaReviewRepository jpa;
    private final ReviewFactory reviewFactory;

    @Override
    public Optional<Review> query(String id) {
        Optional<ReviewEntity> entity = jpa.findById(id);
        return Optional.of(entity.get().toDomain(reviewFactory));
    }
}
