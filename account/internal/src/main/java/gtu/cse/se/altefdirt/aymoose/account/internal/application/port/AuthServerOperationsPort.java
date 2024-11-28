package gtu.cse.se.altefdirt.aymoose.account.internal.application.port;

import java.util.List;
import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AuthDetails;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface AuthServerOperationsPort {
    
    List<String> getRoles(String accountId);

    Optional<Boolean> hasRole(String accountId, String role);

    Optional<Boolean> removeRole(String accountId, String role);

    Optional<Boolean> addRole(String accountId, String role);

    Optional<AggregateId> register(String username, String password, String mailAddress);

    Optional<AuthDetails> getDetails(AggregateId userId);
}
