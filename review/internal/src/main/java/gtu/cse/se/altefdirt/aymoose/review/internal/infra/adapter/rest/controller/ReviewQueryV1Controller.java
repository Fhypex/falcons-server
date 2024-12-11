package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.service.ReviewService;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Rating;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewRepository;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto.ReviewResponseDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class ReviewQueryV1Controller {

    private final ReviewService service;
    private final ReviewRepository repository;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String USER = "user";
        private static final String FACILITY = "facility";
        private static final String RATING_EQ = "ratingEq";
        private static final String RATING_GT = "ratingGt";
        private static final String RATING_LT = "ratingLt";
    }

    @GetMapping(value = "/reviews")
    List<ReviewResponseDTO> getAllReviews() {
        return repository.findAll().stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review)))
                .toList();
    }

    @GetMapping(value = "/review/{id}")
    ReviewResponseDTO getReviewById(@PathVariable(Parameter.ID) UUID id) {
        return ReviewResponseDTO.fromView(service.denormalize(repository.findById(AggregateId.fromUUID(id)).get()));
    }

    @GetMapping(value = "/reviews", params = Parameter.USER)
    List<ReviewResponseDTO> getReviewsByUser(@RequestParam(Parameter.USER) UUID userId) {
        return repository.findByUserId(AggregateId.fromUUID(userId)).stream()
                .map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).toList();
    }

    @GetMapping(value = "/reviews", params = Parameter.FACILITY)
    List<ReviewResponseDTO> getReviewsByFacility(@RequestParam(Parameter.FACILITY) UUID facilityId) {
        return repository.findByFacilityId(AggregateId.fromUUID(facilityId)).stream()
                .map(review -> ReviewResponseDTO.fromView(service.denormalize(review)))
                .toList();
    }

    @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY })
    List<ReviewResponseDTO> getReviewsByUserAndFacility(@RequestParam(Parameter.USER) UUID userId,
            @RequestParam(Parameter.FACILITY) UUID facilityId) {
        return repository.findByUserIdAndFacilityId(AggregateId.fromUUID(userId), AggregateId.fromUUID(facilityId))
                .stream()
                .map(review -> ReviewResponseDTO.fromView(service.denormalize(review)))
                .toList();
    }

    @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY, Parameter.RATING_LT })
    List<ReviewResponseDTO> getReviewsByUserAndFacilityAndRatingLesser(@RequestParam(Parameter.USER) UUID userId,
            @RequestParam(Parameter.FACILITY) UUID facilityId, @RequestParam(Parameter.RATING_LT) String rating) {
        return repository
                .findByUserIdFacilityIdAndRatingLesserThan(AggregateId.fromUUID(userId),
                        AggregateId.fromUUID(facilityId), Rating.fromRound(rating, false))
                .stream()
                .map(review -> ReviewResponseDTO.fromView(service.denormalize(review)))
                .toList();
    }

    @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY, Parameter.RATING_GT })
    List<ReviewResponseDTO> getReviewsByUserAndFacilityAndRatingGreater(@RequestParam(Parameter.USER) UUID userId,
            @RequestParam(Parameter.FACILITY) UUID facilityId, @RequestParam(Parameter.RATING_GT) String rating) {
        return repository
                .findByUserIdFacilityIdAndRatingGreaterThan(AggregateId.fromUUID(userId),
                        AggregateId.fromUUID(facilityId), Rating.fromRound(rating, false))
                .stream()
                .map(review -> ReviewResponseDTO.fromView(service.denormalize(review)))
                .toList();
    }

    @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY, Parameter.RATING_EQ })
    List<ReviewResponseDTO> getReviewsByFacilityAndRatingEqual(@RequestParam(Parameter.USER) UUID userId,
            @RequestParam(Parameter.FACILITY) UUID facilityId, @RequestParam(Parameter.RATING_EQ) String rating) {
        return repository
                .findByUserIdFacilityIdAndRatingEqual(AggregateId.fromUUID(userId), AggregateId.fromUUID(facilityId),
                        Rating.fromRound(rating, false))
                .stream()
                .map(review -> ReviewResponseDTO.fromView(service.denormalize(review)))
                .toList();
    }
}
