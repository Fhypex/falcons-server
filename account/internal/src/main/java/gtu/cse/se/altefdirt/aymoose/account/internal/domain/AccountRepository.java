package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import java.util.List;
import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface AccountRepository {
    
    Account save(Account account);

    Optional<Account> findById(AggregateId id);

    List<Account> findAll();

}
