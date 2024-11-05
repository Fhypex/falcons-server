package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.UserOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Component
public class UserOperationsAdapter implements UserOperationsPort {
    
    // TODO: Implement getting the author details from user service
    @Override
    public String getAuthor(AggregateId id) {
        return "Mehmet Hayrullah Ozkul";
    }


    // TODO: Implement getting the author details from user service
    @Override
    public List<String> getAuthors(List<AggregateId> ids) {
        return List.of("Mehmet Hayrullah Ozkul");
    }
}
