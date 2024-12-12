package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import java.time.Instant;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;

@Component
public class AccountFactory {

    public Account create(FullName fullName) {
        return new Account(AggregateId.generate(), fullName, Instant.now(), true);
    }

    public Account load(AggregateId id, FullName fullName, Instant createdAt, Boolean isActive) {
        return new Account(id, fullName, createdAt, isActive);
    }
}
