package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.SingleValueObject;

public record MailAddress(String value) implements SingleValueObject<String> {
    public MailAddress {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("MailAddress cannot be null or blank");
        }
    }
}