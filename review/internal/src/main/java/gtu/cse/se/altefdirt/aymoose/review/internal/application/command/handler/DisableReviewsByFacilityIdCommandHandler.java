package gtu.cse.se.altefdirt.aymoose.review.internal.application.command.handler;

import org.apache.commons.lang3.NotImplementedException;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.command.DisableReviewsByFacilityId;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;

@RegisterHandler
public class DisableReviewsByFacilityIdCommandHandler implements CommandHandler<DisableReviewsByFacilityId, Long> {

    @Override
    public Long handle(DisableReviewsByFacilityId command) {
        throw new NotImplementedException();
    }
}