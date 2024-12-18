package gtu.cse.se.altefdirt.aymoose.review.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.DeleteReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class DeleteReviewCommandHandler implements CommandHandler<DeleteReview, Integer> {

    private final ReviewRepository repository;

    @Override
    public Integer handle(DeleteReview command) {
        return repository.deleteById(AggregateId.fromUUID(command.reviewId()));
    }
}