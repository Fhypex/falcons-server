package gtu.cse.se.altefdirt.aymoose.account.internal.application.port;

import java.util.List;
import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AuthDetails;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface KeycloakOperationPort {
    
    List<String> getRoles(AggregateId userId);

    Optional<Boolean> hasRole(AggregateId userId, String role);

    Optional<Boolean> removeRole(AggregateId userId, String role);

    Optional<Boolean> addRole(AggregateId userId, String role);

    AggregateId register(String mailAddress, String password);

    Optional<AuthDetails> getDetails(AggregateId userId);

    int delete(AggregateId userId);
}
