package gtu.cse.se.altefdirt.aymoose.account.internal.application.model;

import java.util.List;

public record AuthDetails(String username, String mailAddress, List<String> roles) {
    public AuthDetails {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (mailAddress == null || mailAddress.isBlank()) {
            throw new IllegalArgumentException("Mail address cannot be null or blank");
        }
        if (roles == null) {
            throw new IllegalArgumentException("Roles cannot be null or empty");
        }
    }
}
