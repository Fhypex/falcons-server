package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.time.Instant;

import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValueObject;
import jakarta.validation.constraints.NotNull;

@ValueObject
public record UpdatedAt(
    @NotNull Instant creationTime) implements SingleValueObject<Instant> {   
        
    public static UpdatedAt now() {
        return new UpdatedAt(Instant.now());
    }

    @Override
    public Instant value() {
        return creationTime;
    }
}