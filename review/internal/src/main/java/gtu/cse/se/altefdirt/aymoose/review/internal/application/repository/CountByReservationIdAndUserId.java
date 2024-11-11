package gtu.cse.se.altefdirt.aymoose.review.internal.application.repository;

import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValidAggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.readmodel.Query;
import jakarta.validation.Valid;

@Query
public interface CountByReservationIdAndUserId {
    int query(@Valid @ValidAggregateId String reservationId, @Valid @ValidAggregateId String userId);
}
