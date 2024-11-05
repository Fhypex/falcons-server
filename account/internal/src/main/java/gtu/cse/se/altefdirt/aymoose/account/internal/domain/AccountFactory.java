package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;

@Component
public class AccountFactory {
    
    public Account create(String accountname, FullName fullName, String profilePicture, Instant createdAt) {
        return new Account(AggregateId.generate(), accountname, fullName, profilePicture, createdAt, true);
    }


    public Account load(AggregateId id, 
                        String accountname,
                        FullName fullName, 
                        String profilePicture, 
                        Instant createdAt, 
                        Boolean isActive) {
        return new Account(id, accountname, fullName, profilePicture, createdAt, isActive);
    }
}
