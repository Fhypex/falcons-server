package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.AuthDetails;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.KeycloakOperationPort;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.SecurityConstants;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class KeycloakOperationAdapter implements KeycloakOperationPort {

    private Keycloak keycloak;
    private String realm;
    private String adminClient;

    public KeycloakOperationAdapter(@Autowired Keycloak keycloak,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.admin.client.uuid}") String adminClient) {
        this.keycloak = keycloak;
        this.realm = realm;
        this.adminClient = adminClient;
    }

    @Override
    public List<String> getRoles(AggregateId userId) {
        // Get the realm resource from Keycloak
        RealmResource realmResource = keycloak.realm(realm);

        // Get the user resource from the realm
        UserResource user = realmResource.users().get(userId.toString());

        return extractUserRoles(user);
    }

    @Override
    public Optional<Boolean> hasRole(AggregateId userId, String role) {
        // Get the realm resource from Keycloak
        RealmResource realmResource = keycloak.realm(realm);
        // Get the user resource from the realm
        UserResource user = realmResource.users().get(userId.toString());
        RoleMappingResource roleMappingResource = user.roles();
        List<RoleRepresentation> roles = roleMappingResource.realmLevel().listAll();
        for (RoleRepresentation roleRepresentation : roles) {
            if (roleRepresentation.getName().equals(role)) {
                return Optional.of(true);
            }
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> removeRole(AggregateId userId, String role) {
        // Get the realm resource from Keycloak
        RealmResource realmResource = keycloak.realm(realm);

        // Get the user resource from the realm
        UserResource user = realmResource.users().get(userId.toString());

        RoleMappingResource roleMappingResource = user.roles();
        List<RoleRepresentation> roles = roleMappingResource.realmLevel().listAll();
        for (RoleRepresentation roleRepresentation : roles) {
            if (roleRepresentation.getName().equals(role)) {
                roleMappingResource.realmLevel().remove(Collections.singletonList(roleRepresentation));
                return Optional.of(true);
            }
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> addRole(AggregateId userId, String role) {
        try {
            // Get the realm resource from Keycloak
            RealmResource realmResource = keycloak.realm(realm);
            // Get the user resource from the realm
            UserResource user = realmResource.users().get(userId.toString());
            RoleMappingResource roleMappingResource = user.roles();
            roleMappingResource.realmLevel().add(Collections.singletonList(realmResource.roles().get(role).toRepresentation()));
            return Optional.of(true);
        } catch (Exception e) {
            // Log the exception (optional)
            log.error("Error adding role to the user", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public AggregateId register(String email, String password) {
        try {
            // Get the realm resource from Keycloak
            RealmResource realmResource = keycloak.realm(realm);
            // Create a new UserRepresentation object
            UserRepresentation user = new UserRepresentation();
            user.setEmail(email);
            user.setEnabled(true);
            user.setFirstName("demo-name");
            user.setLastName("demo-last-name");
            user.setCredentials(Collections.singletonList(createPasswordCredential(password)));
            user.setRealmRoles(Collections.singletonList("101e410b-c892-461e-abba-5e99b3c047ce"));
            // Add the user to the realm
            Response response = realmResource.users().create(user);
            // Check if the user was created successfully
            if (response.getStatus() != 201) {
                if(response.getStatus() == 409) {
                    throw new RuntimeException("User already exists");
                }
                throw new RuntimeException("User registration failed");
            }   
            String userId = getCreatedId(response);
            UserResource createdUser = realmResource.users().get(userId);
            createdUser.roles().realmLevel().add(Collections.singletonList(realmResource.roles().get(SecurityConstants.ROLE_USER).toRepresentation()));
            return AggregateId.fromString(userId);
        } catch (Exception e) {
            log.error("Error registering user", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    private String getCreatedId(Response response) {
    URI location = response.getLocation();
    if (!response.getStatusInfo().equals(Response.Status.CREATED)) {
        Response.StatusType statusInfo = response.getStatusInfo();
        throw new WebApplicationException("Create method returned status " +
                statusInfo.getReasonPhrase() + " (Code: " + statusInfo.getStatusCode() + "); expected status: Created (201)", response);
    }
    if (location == null) {
        return null;
    }
    String path = location.getPath();
    return path.substring(path.lastIndexOf('/') + 1);
}

    @Override
    public int delete(AggregateId userId) {
        try {
            RealmResource realmResource = keycloak.realm(realm);
            UserResource user = realmResource.users().get(userId.toString());
            if (user == null) {
                return 0; // User not found
            }
            user.remove();
            return 1; // User deleted successfully
        } catch (Exception e) {
            // Log the exception (optional)
            log.error("Error occurred during deleting user", e);
            return 0;
        }
    }

    @Override
    public Optional<AuthDetails> getDetails(AggregateId userId) {
        try {
            RealmResource realmResource = keycloak.realm(realm);
            UserResource user = realmResource.users().get(userId.toString());
            if (user == null) {
                return Optional.empty(); // User not found
            }
            UserRepresentation userRepresentation = user.toRepresentation();
            List<String> realmRoles = extractUserRoles(user);
            return Optional.of(new AuthDetails(
                    userRepresentation.getEmail(),
                    (realmRoles != null) ? realmRoles
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


    private List<String> extractUserRoles(UserResource user) {
        RoleMappingResource roleMappingResource = user.roles();
        List<RoleRepresentation> roles = roleMappingResource.realmLevel().listAll();
        List<String> rolesStrings = new LinkedList<>();
        for (RoleRepresentation role : roles) {
            rolesStrings.add(role.getName());
        }
        return rolesStrings;
    }
}
