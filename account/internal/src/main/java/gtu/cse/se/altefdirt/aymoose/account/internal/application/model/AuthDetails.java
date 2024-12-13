package gtu.cse.se.altefdirt.aymoose.account.internal.application.model;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.core.infra.security.SecurityConstants.AccessRole;

public record AuthDetails(String mailAddress, List<AccessRole> roles) {
    public AuthDetails {
        if (mailAddress == null || mailAddress.isBlank()) {
            throw new IllegalArgumentException("Mail address cannot be null or blank");
        }
        if (roles == null) {
            throw new IllegalArgumentException("Roles cannot be null or empty");
        }
    }
}
