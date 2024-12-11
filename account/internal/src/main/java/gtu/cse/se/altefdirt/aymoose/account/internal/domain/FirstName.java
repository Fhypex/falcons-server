package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SingleValueObject;

public record FirstName(String value) implements SingleValueObject<String> {
    public FirstName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Firstname cannot be null or blank");
        }
    }
}