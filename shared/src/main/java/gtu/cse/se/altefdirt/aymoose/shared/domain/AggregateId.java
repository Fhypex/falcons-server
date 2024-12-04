package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AggregateId(
        @NotNull String id) implements SingleValueObject<String>, Serializable {

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID().toString());
    }

    public static AggregateId of(UUID value) {
        return new AggregateId(value.toString());
    }

    public static AggregateId from(String value) {
        return new AggregateId(UUID.fromString(value).toString());
    }

    public static AggregateId from(UUID value) {
        return new AggregateId(value.toString());
    }

    @Override
    public String value() {
        return id;
    }
}
