package gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt;

import java.util.Collection;

import org.springframework.security.oauth2.jwt.Jwt;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

/**
 * JwtUser is a class that extends Jwt and adds {@code UUID} field as the
 * {@code User's ID} with respoected user's roles.
 * 
 * It essentialy a wrapper class for Jwt.
 */
public class JwtUser extends Jwt {

    private final AggregateId id;
    private final Collection<String> roles;

    public JwtUser(Jwt jwt, AggregateId id, Collection<String> roles) {
        super(jwt.getTokenValue(), jwt.getIssuedAt(), jwt.getExpiresAt(), jwt.getHeaders(), jwt.getClaims());
        this.id = id;
        this.roles = roles;
    }

    public final AggregateId id() {
        return id;
    }

    public final AggregateId userId() {
        return id;
    }

    public final Collection<String> roles() {
        return this.roles;
    }

    public final boolean hasRole(String role) {
        return this.roles.contains(role);
    }
}