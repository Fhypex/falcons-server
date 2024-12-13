package gtu.cse.se.altefdirt.aymoose.review.internal.application.port;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.application.UserData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface AccountOperationPort {

    UserData getAccount(AggregateId id);

    List<UserData> getAccounts(List<AggregateId> ids);
}
