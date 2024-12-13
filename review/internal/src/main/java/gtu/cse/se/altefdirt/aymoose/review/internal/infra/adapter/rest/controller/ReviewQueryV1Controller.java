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
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto.ReviewResponseWithAllDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
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
        List<ReviewResponseWithAllDTO> getAllReviews() {
                return repository.findAll().stream()
                                .map(review -> ReviewResponseWithAllDTO.fromView(service.denormalize(review)))
                                .toList();
        }

        @GetMapping(value = "/review/{id}")
        ReviewResponseWithAllDTO getReviewById(@PathVariable(Parameter.ID) UUID id) {
                return ReviewResponseWithAllDTO
                                .fromView(service.denormalize(repository.findById(AggregateId.fromUUID(id)).get()));
        }

        @GetMapping(value = "/reviews", params = Parameter.USER)
        List<ReviewResponseWithAllDTO> getReviewsByUser(@RequestParam(Parameter.USER) UUID userId) {
                return service.denormalizeForSameUser(repository.findByUserId(AggregateId.fromUUID(userId)))
                                .stream()
                                .map(review -> ReviewResponseWithAllDTO.fromView(review))
                                .toList();
        }

        @GetMapping(value = "/reviews", params = Parameter.FACILITY)
        List<ReviewResponseWithAllDTO> getReviewsByFacility(@RequestParam(Parameter.FACILITY) UUID facilityId) {
                return service.denormalizeForSameFacility(repository.findByFacilityId(AggregateId.fromUUID(facilityId)))
                                .stream()
                                .map(review -> ReviewResponseWithAllDTO.fromView(review))
                                .toList();
        }

        @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY })
        List<ReviewResponseWithAllDTO> getReviewsByUserAndFacility(@RequestParam(Parameter.USER) UUID userId,
                        @RequestParam(Parameter.FACILITY) UUID facilityId) {
                return service.denormalizeForSameFacilityAndUser(
                                repository.findByUserIdAndFacilityId(AggregateId.fromUUID(userId),
                                                AggregateId.fromUUID(facilityId)))
                                .stream()
                                .map(review -> ReviewResponseWithAllDTO.fromView(review))
                                .toList();
        }

        @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY, Parameter.RATING_LT })
        List<ReviewResponseWithAllDTO> getReviewsByUserAndFacilityAndRatingLesser(
                        @RequestParam(Parameter.USER) UUID userId,
                        @RequestParam(Parameter.FACILITY) UUID facilityId,
                        @RequestParam(Parameter.RATING_LT) String rating) {
                return service.denormalizeForSameFacilityAndUser(
                                repository.findByUserIdFacilityIdAndRatingLesserThan(AggregateId.fromUUID(userId),
                                                AggregateId.fromUUID(facilityId), Rating.fromRound(rating, false)))
                                .stream()
                                .map(review -> ReviewResponseWithAllDTO.fromView(review))
                                .toList();
        }

        @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY, Parameter.RATING_GT })
        List<ReviewResponseWithAllDTO> getReviewsByUserAndFacilityAndRatingGreater(
                        @RequestParam(Parameter.USER) UUID userId,
                        @RequestParam(Parameter.FACILITY) UUID facilityId,
                        @RequestParam(Parameter.RATING_GT) String rating) {
                return service.denormalizeForSameFacilityAndUser(
                                repository.findByUserIdFacilityIdAndRatingGreaterThan(AggregateId.fromUUID(userId),
                                                AggregateId.fromUUID(facilityId), Rating.fromRound(rating, false)))
                                .stream()
                                .map(review -> ReviewResponseWithAllDTO.fromView(review))
                                .toList();
        }

        @GetMapping(value = "/reviews", params = { Parameter.USER, Parameter.FACILITY, Parameter.RATING_EQ })
        List<ReviewResponseWithAllDTO> getReviewsByFacilityAndRatingEqual(@RequestParam(Parameter.USER) UUID userId,
                        @RequestParam(Parameter.FACILITY) UUID facilityId,
                        @RequestParam(Parameter.RATING_EQ) String rating) {
                return service.denormalizeForSameFacilityAndUser(
                                repository.findByUserIdFacilityIdAndRatingEqual(AggregateId.fromUUID(userId),
                                                AggregateId.fromUUID(facilityId), Rating.fromRound(rating, false)))
                                .stream()
                                .map(review -> ReviewResponseWithAllDTO.fromView(review))
                                .toList();
        }
}
