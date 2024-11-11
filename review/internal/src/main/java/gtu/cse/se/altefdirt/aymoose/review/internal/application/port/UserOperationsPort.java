package gtu.cse.se.altefdirt.aymoose.review.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.Author;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface UserOperationsPort {

    Author getAuthor(AggregateId id);
    
    List<Author> getAuthors(List<AggregateId> ids);
}
