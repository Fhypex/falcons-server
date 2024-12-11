package gtu.cse.se.altefdirt.aymoose.account.api.provider;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

public interface AccountProvider {

    FullName getFullNameById(AggregateId id);

    List<FullName> getFullNamesByIds(List<AggregateId> ids);
}
