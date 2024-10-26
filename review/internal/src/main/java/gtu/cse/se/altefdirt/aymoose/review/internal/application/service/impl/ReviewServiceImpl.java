package gtu.cse.se.altefdirt.aymoose.review.internal.application.service.impl;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.Author;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.UserOperationsPort;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.service.ReviewService;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ReviewServiceImpl implements ReviewService {
    
    private final UserOperationsPort userOperationsPort;

    @Override
    public boolean isReviewExist(String reservationId, String userId) {
        return false;
    }

    @Override
    public ReviewView denormalize(Review review) {
        Author author = userOperationsPort.getAuthor(review.userId());
        return new ReviewView(review, author.fullName().value());
    }
}
