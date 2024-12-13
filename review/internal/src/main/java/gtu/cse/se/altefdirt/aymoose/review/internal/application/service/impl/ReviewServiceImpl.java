package gtu.cse.se.altefdirt.aymoose.review.internal.application.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.AccountOperationPort;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.service.ReviewService;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.application.UserData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
class ReviewServiceImpl implements ReviewService {

    private final AccountOperationPort accountOperationPort;
    private final FacilityOperationPort facilityOperationPort;

    @Override
    public boolean isReviewExist(AggregateId reservationId, AggregateId userId) {
        return false;
    }

    @Override
    public ReviewView denormalize(Review review) {
        log.debug("Denormalizing review: {}", review);
        UserData author = accountOperationPort.getAccount(review.userId());
        log.debug("Review author: {}", author);
        String facilityName = facilityOperationPort.getFacilityName(review.getFacilityId());
        log.debug("Review author: {}, facility: {}", author, facilityName);
        return new ReviewView(review, author, facilityName);
    }

    @Override
    public List<ReviewView> denormalize(List<Review> reviews) {
        return reviews.stream().map(this::denormalize).toList();
    }

    @Override
    public List<ReviewView> denormalizeForSameUser(List<Review> reviews) {
        UserData author = accountOperationPort.getAccount(reviews.get(0).userId());
        return reviews.stream().map(review -> denormalizeWithAuthor(review, author)).toList();
    }

    @Override
    public List<ReviewView> denormalizeForSameFacility(List<Review> reviews) {
        String facilityName = facilityOperationPort.getFacilityName(reviews.get(0).getFacilityId());
        return reviews.stream().map(review -> denormalizeWithFacilityName(review, facilityName)).toList();
    }

    @Override
    public List<ReviewView> denormalizeForSameFacilityAndUser(List<Review> reviews) {
        UserData author = accountOperationPort.getAccount(reviews.get(0).userId());
        String facilityName = facilityOperationPort.getFacilityName(reviews.get(0).getFacilityId());
        return reviews.stream().map(review -> new ReviewView(review, author, facilityName)).toList();
    }

    private ReviewView denormalizeWithAuthor(Review review, UserData author) {
        String facilityName = facilityOperationPort.getFacilityName(review.getFacilityId());
        return new ReviewView(review, author, facilityName);
    }

    private ReviewView denormalizeWithFacilityName(Review review, String facilityName) {
        UserData author = accountOperationPort.getAccount(review.userId());
        return new ReviewView(review, author, facilityName);
    }
}
