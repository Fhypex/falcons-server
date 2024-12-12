package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.io.Serializable;
import java.util.UUID;

import org.modelmapper.internal.util.Assert;

import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.validation.constraints.NotNull;

public record AggregateId(
        @NotNull UUID id) implements SingleValueObject<UUID>, Serializable {

    public static AggregateId generate() {
        return new AggregateId(UUID.randomUUID());
    }

    public static AggregateId of(UUID value) {
        return new AggregateId(value);
    }

    public static AggregateId fromUUID(UUID value) {
        Assert.notNull(value, "UUID must not be null");
        return new AggregateId(value);
    }

    public static AggregateId fromString(String value) {
        return new AggregateId(UUID.fromString(value));
    }

    @JsonValue
    @Override
    public UUID value() {
        return id;
    }

    public final String asString() {
        return id.toString();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
