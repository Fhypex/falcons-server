package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AuthDetails;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.AuthServerOperationsPort;
import gtu.cse.se.altefdirt.aymoose.account.internal.domain.UserId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class AuthServerOperationsAdapter implements AuthServerOperationsPort {

    private Keycloak keycloak;
    private String realm;
    private String adminClient;

    public AuthServerOperationsAdapter(@Autowired Keycloak keycloak,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.admin.client.uuid}") String adminClient) {
        this.keycloak = keycloak;
        this.realm = realm;
        this.adminClient = adminClient;
    }

    @Override
    public List<String> getRoles(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Boolean> hasRole(String userId, String role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Boolean> removeRole(String userId, String role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Boolean> addRole(String userId, String role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<UserId> register(String username, String password, String email) {
        try {
            System.out.println("username: " + username);
            System.out.println("email: " + email);
            System.out.println("password: " + password);
            // Get the realm resource from Keycloak
            RealmResource realmResource = keycloak.realm(realm);

            // Create a new UserRepresentation object
            UserRepresentation user = new UserRepresentation();
            // user.setId(userId);
            user.setUsername(username);
            user.setEmail(email);
            user.setEnabled(true);
            user.setCredentials(Collections.singletonList(createPasswordCredential(password)));

            // Add the user to the realm
            Response response = realmResource.users().create(user);

            // Check if the user was created successfully
            if (response.getStatus() != 201) {
                // Log the error (optional)
                System.err.println("Error registering user: " + response.getStatusInfo().getReasonPhrase());
                // Return empty result in case of failure

                return Optional.empty();
            }
            System.out.println("response: " + response.getLocation().getPath());
            String userId = response.getLocation().getPath().split("(.)*users/")[1];
            System.out.println("userId: " + userId);

            // Return successful result
            return Optional.of(UserId.from(userId));
        } catch (Exception e) {
            // Log the exception (optional)
            System.err.println("Error registering user: " + e.getMessage());
            // Return empty result in case of failure
            return Optional.empty();
        }
    }

    @Override
    public Optional<AuthDetails> getDetails(UserId userId) {
        try {
            RealmResource realmResource = keycloak.realm(realm);
            UserResource user = realmResource.users().get(userId.value());
            if (user == null) {
                return Optional.empty(); // User not found
            }
            UserRepresentation userRepresentation = user.toRepresentation();
            return Optional.of(new AuthDetails(
                    userRepresentation.getUsername(),
                    userRepresentation.getEmail(),
                    (userRepresentation.getRealmRoles() != null) ? userRepresentation.getRealmRoles()
                            : Collections.emptyList()));

        } catch (Exception e) {
            // Log the exception (optional)
            log.error("Error occurred during getting details of user", e);
            return Optional.empty();
        }
    }

    // Helper method to create password credential for the user
    private CredentialRepresentation createPasswordCredential(String password) {
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setType("password");
        passwordCredential.setValue(password);
        passwordCredential.setTemporary(false); // Set to false if password is permanent
        return passwordCredential;
    }
}
