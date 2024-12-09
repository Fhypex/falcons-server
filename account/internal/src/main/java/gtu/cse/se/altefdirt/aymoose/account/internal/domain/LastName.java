package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SingleValueObject;

public record LastName(String value) implements SingleValueObject<String> {
    public LastName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be null or blank");
        }
    }
}