package gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

/**
 * An implementation of an {@link AbstractOAuth2TokenAuthenticationToken} representing a
 * {@link Jwt} {@code Authentication} for customized user
 * @see AbstractOAuth2TokenAuthenticationToken
 * @see Jwt
 */
@Transient
public class SecuredUser extends AbstractOAuth2TokenAuthenticationToken<Jwt> {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final AggregateId id;

	public SecuredUser(Jwt jwt) {
		super(jwt);
		this.id = AggregateId.from(jwt.getSubject());
	}

	public SecuredUser(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
		super(jwt, authorities);
		this.id = AggregateId.from(jwt.getSubject());
		this.setAuthenticated(true);
	}

	public SecuredUser(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String id) {
		super(jwt, authorities);
		this.setAuthenticated(true);
		this.id = AggregateId.from(id);
	}

	@Override
	public Map<String, Object> getTokenAttributes() {
		return this.getToken().getClaims();
	}

	@Override
	public String getName() {
		return this.id.value();
	}

	public AggregateId getId() {
		return this.id;
	}

	public AggregateId id() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) 
	{
		return super.equals(obj);
	}

	@Override
	public int hashCode() 
	{
		return super.hashCode();
	}
}