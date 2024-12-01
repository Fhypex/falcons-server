package gtu.cse.se.altefdirt.aymoose.core.infra.security.jwt;

import org.springframework.security.oauth2.jwt.Jwt;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtils {

    // Example utility method
    public AggregateId extractUserId(Jwt jwt) {
        return AggregateId.from(jwt.getClaimAsString("sub"));
    }
}