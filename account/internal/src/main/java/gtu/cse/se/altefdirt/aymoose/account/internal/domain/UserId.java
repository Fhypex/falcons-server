package gtu.cse.se.altefdirt.aymoose.account.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public record UserId(String value) {
    public UserId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be null or blank");
        }
    }

    public static UserId generate() {
        /* return new UserId("aymoose|" + AggregateId.generate().value()); */
        return new UserId(AggregateId.generate().value());
    }

    public static UserId from(String value) {
        return new UserId(value);
    }

    private UserId(AggregateId aggregateId) {
        this("aymoose|" + aggregateId.value());
    }

    public String value() {
        return value;
    }
}