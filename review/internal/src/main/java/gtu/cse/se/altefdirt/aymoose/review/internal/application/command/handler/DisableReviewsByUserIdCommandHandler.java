package gtu.cse.se.altefdirt.aymoose.review.internal.application.command.handler;

import org.apache.commons.lang3.NotImplementedException;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.DisableReviewsByUserId;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;

@RegisterHandler
public class DisableReviewsByUserIdCommandHandler implements CommandHandler<DisableReviewsByUserId, Long> {

    @Override
    public Long handle(DisableReviewsByUserId command) {
        throw new NotImplementedException();
    }
}