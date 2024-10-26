package gtu.cse.se.altefdirt.aymoose.court.internal.application.command.handler;

import org.apache.commons.lang3.NotImplementedException;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.command.DisableCourtsByFacilityId;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;

@RegisterHandler
public class DisableCourtsByFacilityIdCommandHandler implements CommandHandler<DisableCourtsByFacilityId, Long> {
    
    @Override
    public Long handle(DisableCourtsByFacilityId command) {
        throw new NotImplementedException();
    }
}