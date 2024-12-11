package gtu.cse.se.altefdirt.aymoose.review.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

public interface UserOperationPort {

    FullName getAuthor(AggregateId id);

    List<FullName> getAuthors(List<AggregateId> ids);
}
