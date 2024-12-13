package gtu.cse.se.altefdirt.aymoose.account.api.provider;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.application.UserData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface AccountProvider {

    UserData getAccountById(AggregateId id);

    List<UserData> getAccountsByIds(List<AggregateId> ids);
}
