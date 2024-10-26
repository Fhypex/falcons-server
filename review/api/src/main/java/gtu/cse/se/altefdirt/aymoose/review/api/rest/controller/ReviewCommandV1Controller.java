package gtu.cse.se.altefdirt.aymoose.review.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.CreateReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.repository.CountByReservationIdAndUserId;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.service.ReviewService;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.review.api.rest.dto.CreateReviewRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class ReviewCommandV1Controller {

    private final CommandRunner runEnvironment;
    private final CountByReservationIdAndUserId countByReservationIdAndUserId;

    @PostMapping("/reviews")
    public Response<ReviewView> createReview(@RequestBody CreateReviewRequestDTO request) {
        ReviewView view = runEnvironment.run(new CreateReview(request.reservationId(), request.userId(), request.title(), request.content(), request.rating()));
        return Response.success(view, "Review created successfully");
    }

    @PostMapping("/workspace")
    public void createReview(@RequestBody String reservationId, String userId) {
        int count = countByReservationIdAndUserId.query(reservationId, userId);
    }


}
