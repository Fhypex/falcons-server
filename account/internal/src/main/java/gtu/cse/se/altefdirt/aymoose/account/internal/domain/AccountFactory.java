package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import java.time.Instant;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;

@Component
public class AccountFactory {

    public Account create(FullName fullName) {
        return new Account(AggregateId.generate(), fullName, PhoneNumber.empty(), Instant.now(), true);
    }

    public Account load(AggregateId id, FullName fullName, PhoneNumber phoneNumber, Instant createdAt,
            Boolean isActive) {
        return new Account(id, fullName, phoneNumber, createdAt, isActive);
    }
}
