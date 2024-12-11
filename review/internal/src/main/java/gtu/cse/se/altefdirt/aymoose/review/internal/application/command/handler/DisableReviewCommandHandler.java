package gtu.cse.se.altefdirt.aymoose.review.internal.application.command.handler;

import org.apache.commons.lang3.NotImplementedException;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.DisableReview;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;

@RegisterHandler
public class DisableReviewCommandHandler implements CommandHandler<DisableReview, Review> {

    @Override
    public Review handle(DisableReview command) {
        throw new NotImplementedException();
    }
}