package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.Author;
import gtu.cse.se.altefdirt.aymoose.review.internal.application.port.UserOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Component
public class UserOperationsAdapter implements UserOperationsPort {
    
    // TODO: Implement getting the author details from user service
    @Override
    public Author getAuthor(AggregateId id) {
        return new Author(AggregateId.generate(), FullName.of("Mehmet Hayrullah", "Ozkul"));
    }


    // TODO: Implement getting the author details from user service
    @Override
    public List<Author> getAuthors(List<AggregateId> ids) {
        return List.of(new Author(AggregateId.generate(), FullName.of("Mehmet Hayrullah", "Ozkul")));
    }
}
