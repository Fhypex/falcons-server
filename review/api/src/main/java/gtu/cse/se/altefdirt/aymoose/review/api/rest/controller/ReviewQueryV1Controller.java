package gtu.cse.se.altefdirt.aymoose.review.api.rest.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.review.api.rest.dto.ReviewResponseDTO;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindAll;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByFacilityId;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindById;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByUserId;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByUserIdAndFacilityId;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByUserIdAndFacilityIdAndRatingEqual;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByUserIdAndFacilityIdAndRatingGreater;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.FindByUserIdAndFacilityIdAndRatingLesser;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.service.ReviewService;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class ReviewQueryV1Controller {

    private final ReviewService service;
    private final FindAll findAll;
    private final FindById findByAggregateId;
    private final FindByUserId findByUserId;
    private final FindByFacilityId findByFacilityId;
    private final FindByUserIdAndFacilityId findByUserIdAndFacilityId;
    private final FindByUserIdAndFacilityIdAndRatingGreater findByUserIdAndFacilityIdAndRatingGreater;
    private final FindByUserIdAndFacilityIdAndRatingLesser findByUserIdAndFacilityIdAndRatingLesser;
    private final FindByUserIdAndFacilityIdAndRatingEqual findByUserIdAndFacilityIdAndRatingEqual;


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
        return findAll.query().stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/review/{id}")
    List<ReviewResponseDTO> getReviewById(@PathVariable(Parameter.ID) String id) {
        return findByAggregateId.query(id).stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/reviews", params = Parameter.USER)
    List<ReviewResponseDTO> getReviewsByUser(@RequestParam(Parameter.USER) String userId) {
        return findByUserId.query(userId).stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/reviews", params = Parameter.FACILITY)
    List<ReviewResponseDTO> getReviewsByFacility(@RequestParam(Parameter.FACILITY) String facilityId) {
        return findByFacilityId.query(facilityId).stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/reviews", params = {Parameter.USER, Parameter.FACILITY})
    List<ReviewResponseDTO> getReviewsByUserAndFacility(@RequestParam(Parameter.USER) String userId, @RequestParam(Parameter.FACILITY) String facilityId) {
        return findByUserIdAndFacilityId.query(userId, facilityId).stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/reviews", params = {Parameter.USER, Parameter.FACILITY, Parameter.RATING_LT})
    List<ReviewResponseDTO> getReviewsByUserAndFacilityAndRatingLesser(@RequestParam(Parameter.USER) String userId, @RequestParam(Parameter.FACILITY) String facilityId, @RequestParam(Parameter.RATING_LT) String rating) {
        return findByUserIdAndFacilityIdAndRatingLesser.query(userId, facilityId, rating).stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/reviews", params = {Parameter.USER, Parameter.FACILITY, Parameter.RATING_GT})
    List<ReviewResponseDTO> getReviewsByUserAndFacilityAndRatingGreater(@RequestParam(Parameter.USER) String userId, @RequestParam(Parameter.FACILITY) String facilityId, @RequestParam(Parameter.RATING_GT) String rating) {
        return findByUserIdAndFacilityIdAndRatingGreater.query(userId, facilityId, rating).stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/reviews", params = {Parameter.USER, Parameter.FACILITY, Parameter.RATING_EQ})
    List<ReviewResponseDTO> getReviewsByFacilityAndRatingEqual(@RequestParam(Parameter.USER) String userId, @RequestParam(Parameter.FACILITY) String facilityId, @RequestParam(Parameter.RATING_EQ) String rating) {
        return findByUserIdAndFacilityIdAndRatingEqual.query(userId, facilityId, rating).stream().map(review -> ReviewResponseDTO.fromView(service.denormalize(review))).collect(Collectors.toUnmodifiableList());
    }
}
