package gtu.cse.se.altefdirt.aymoose.review.internal.application.command.handler;

import java.util.Optional;
import org.apache.commons.lang3.NotImplementedException;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.UpdateReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class UpdateReviewCommandHandler implements CommandHandler<UpdateReview, Review> {

    private final ReviewRepository repository;

    @Override
    public Review handle(UpdateReview command) {

        Optional<Review> fetch = repository.findById(AggregateId.fromUUID(command.reviewId()));

        if (fetch.isEmpty()) {
            throw new NotImplementedException("Review not found");
        }
        Review review = fetch.get();
        if (command.title() != null && !command.title().isEmpty()) {
            review.updateTitle(command.title());
        }

        if (command.content() != null && !command.content().isEmpty()) {
            review.updateContent(command.content());
        }

        if (command.rating() != null) {
            review.updateRating(command.rating());
        }

        return repository.save(review);
    }
}