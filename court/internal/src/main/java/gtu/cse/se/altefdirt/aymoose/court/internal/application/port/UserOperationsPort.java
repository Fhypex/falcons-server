package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface UserOperationsPort {

    String getAuthor(AggregateId id);
    
    List<String> getAuthors(List<AggregateId> ids);
}
