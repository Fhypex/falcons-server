package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SingleValueObject;

public record Username(String value) implements SingleValueObject<String> {
    public Username {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
    }
}