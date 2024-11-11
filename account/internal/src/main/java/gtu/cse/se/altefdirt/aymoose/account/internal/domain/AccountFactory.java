package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;

@Component
public class AccountFactory {
    
    public Account create(FullName fullName, AggregateId imageId) {
        return new Account(UserId.generate(), fullName, imageId, CreatedAt.now(), true);
    }

    public Account load(UserId id, FullName fullName, AggregateId imageId, CreatedAt createdAt, Boolean isActive) {
        return new Account(id, fullName, imageId, createdAt, isActive);
    }
}
