package gtu.cse.se.altefdirt.aymoose.account.internal.application.port;

import java.util.List;
import java.util.Optional;

public interface AuthServerOperationsPort {
    
    List<String> getRoles(String accountId);

    Optional<Boolean> hasRole(String accountId, String role);

    Optional<Boolean> removeRole(String accountId, String role);

    Optional<Boolean> addRole(String accountId, String role);

    Optional<Boolean> register(String accountId, String username, String mailAddress, String password);
}
