package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.repository;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.CountByReservationIdAndUserId;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindAll;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.JpaReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValidAggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.QueryImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@QueryImpl
@RequiredArgsConstructor
class CountByReservationIdAndUserIdImpl implements CountByReservationIdAndUserId {

    private final JpaReviewRepository jpa;
    private final ReviewFactory reviewFactory;

    @Override
    public int query(@ValidAggregateId @Valid String reservationId, @ValidAggregateId @Valid String userId) {

        return jpa.countByReservationIdAndUserId(reservationId, userId);
    }
    
}
