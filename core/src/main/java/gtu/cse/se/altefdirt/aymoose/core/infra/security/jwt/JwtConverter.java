package gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

import static gtu.cse.se.altefdirt.aymoose.core.infra.security.SecurityConstants.JWT_REALM_ACCESS;
import static gtu.cse.se.altefdirt.aymoose.core.infra.security.SecurityConstants.JWT_ROLES;
import static gtu.cse.se.altefdirt.aymoose.core.infra.security.SecurityConstants.JWT_ROLE_PREFIX;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt);
        if (grantedAuthorities == null) {
            grantedAuthorities = Set.of();
        }
        Collection<GrantedAuthority> authorities = Stream.concat(
                grantedAuthorities.stream(),
                extractRoleAuthorities(jwt).stream()).collect(Collectors.toSet());
        return new JwtUserToken(
                    new JwtUser(jwt, AggregateId.fromString(getPrincipleClaimName(jwt)), extractResourceRolesAsString(jwt)),
                    authorities,
                    getPrincipleClaimName(jwt));
    }

    private String getPrincipleClaimName(Jwt jwt) {
        return jwt.getClaim(JwtClaimNames.SUB);
    }

    @SuppressWarnings({ "unchecked" })
    private Collection<? extends GrantedAuthority> extractRoleAuthorities(Jwt jwt) {
        Collection<String> realmRoles = extractResourceRolesAsString(jwt);
        return realmRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority(JWT_ROLE_PREFIX + role))
                .collect(Collectors.toSet());
    }

    @SuppressWarnings({ "unchecked" })
    private Collection<String> extractResourceRolesAsString(Jwt jwt) {
        Map<String, Object> realmAccess;
        Collection<String> realmRoles;
        realmAccess = jwt.getClaim(JWT_REALM_ACCESS);
        if (realmAccess == null || !(realmAccess instanceof Map)) {
            return Set.of(); // Return an empty set if the value is null or not a Map
        }
        realmRoles = (Collection<String>) realmAccess.get(JWT_ROLES);
        if (realmRoles == null || !(realmRoles instanceof Collection)) {
            return Set.of(); // Return an empty set if the value is null or not a Map
        }
        return realmRoles;
    }
}