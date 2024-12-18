package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.CreateReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.DeleteReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.UpdateReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto.CreateReviewRequestDTO;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto.UpdateReviewRequestDTO;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ReviewCommandV1Controller {

    private final CommandRunner runEnvironment;

    private static final class Parameter {
        private static final String ID = "id";
    }

    @PostMapping("/reviews")
    public Response<UUID> createReview(@RequestBody CreateReviewRequestDTO request) {
        Review review = runEnvironment.run(new CreateReview(request.userId(), request.facilityId(),
                request.title(), request.content(), request.rating()));
        return Response.success(review.id().value(), "Review created successfully");
    }

    @PatchMapping("/review/{id}")
    public Response<UUID> updateReview(@PathVariable(Parameter.ID) UUID id,
            @RequestBody UpdateReviewRequestDTO request) {
        Review review = runEnvironment.run(new UpdateReview(id,
                request.title(), request.content(), request.rating()));
        return Response.success(review.id().value(), "Review updated successfully");
    }

    @DeleteMapping("/review/{id}")
    public Response<UUID> deleteReview(@PathVariable(Parameter.ID) UUID id) {
        Review review = runEnvironment.run(new DeleteReview(id));
        return Response.success(review.id().value(), "Review deleted successfully");
    }
}
