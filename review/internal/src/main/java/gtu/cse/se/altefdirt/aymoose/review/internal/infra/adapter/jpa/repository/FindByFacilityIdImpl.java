package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.repository;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByFacilityId;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.QueryImpl;
import lombok.RequiredArgsConstructor;

@QueryImpl
@RequiredArgsConstructor
class FindByFacilityIdImpl implements FindByFacilityId {

    private final JpaReviewRepository jpa;
    private final ReviewFactory reviewFactory;

    @Override
    public List<Review> query(String facilityId) {
        
        List<ReviewEntity> entities = jpa.findByFacilityId(facilityId);
        return entities.stream().map(entity -> entity.toDomain(reviewFactory)).toList();    
    }
}
