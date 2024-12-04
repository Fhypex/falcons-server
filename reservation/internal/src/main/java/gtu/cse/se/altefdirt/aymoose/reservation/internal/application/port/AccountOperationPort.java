package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

public interface AccountOperationPort {
    
    FullName getFullNameByUserId(AggregateId userId);
}
