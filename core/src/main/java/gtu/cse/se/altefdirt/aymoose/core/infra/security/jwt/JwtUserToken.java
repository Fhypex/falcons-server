package gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;

/**
 * Implementation of an {@link AbstractOAuth2TokenAuthenticationToken}
 * representing a
 * {@link Jwt} {@code Authentication} token.
 * Stores a {@link JwtUserToken} as the principal which stores both the
 * {@link Jwt} and the
 * UUID of the user.
 * 
 * @see JwtUserToken
 * @see AbstractOAuth2TokenAuthenticationToken
 * @see Jwt
 */
@Transient
public class JwtUserToken extends AbstractOAuth2TokenAuthenticationToken<JwtUser> {

   private static final long serialVersionUID = 621L;
   private final String name;

   public JwtUserToken(JwtUser jwtUser) {
      super(jwtUser);
      this.name = jwtUser.getSubject();
   }

   public JwtUserToken(JwtUser jwtUser, Collection<? extends GrantedAuthority> authorities) {
      super(jwtUser, authorities);
      this.setAuthenticated(true);
      this.name = jwtUser.getSubject();
   }

   public JwtUserToken(JwtUser jwtUser, Collection<? extends GrantedAuthority> authorities, String name) {
      super(jwtUser, authorities);
      this.setAuthenticated(true);
      this.name = name;
   }

   @Override
   public Map<String, Object> getTokenAttributes() {
      return ((JwtUser) this.getToken()).getClaims();
   }

   @Override
   public String getName() {
      return this.name;
   }
}