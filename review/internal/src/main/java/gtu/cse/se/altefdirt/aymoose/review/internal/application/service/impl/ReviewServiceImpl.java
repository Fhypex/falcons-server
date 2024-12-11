package gtu.cse.se.altefdirt.aymoose.review.internal.application.service.impl;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.UserOperationPort;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.service.ReviewService;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ReviewServiceImpl implements ReviewService {

    private final UserOperationPort userOperationsPort;

    @Override
    public boolean isReviewExist(AggregateId reservationId, AggregateId userId) {
        return false;
    }

    @Override
    public ReviewView denormalize(Review review) {
        FullName author = userOperationsPort.getAuthor(review.userId());
        return new ReviewView(review, author.value());
    }
}
