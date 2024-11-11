package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.time.Instant;

import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValueObject;
import jakarta.validation.constraints.NotNull;

@ValueObject
public record CreatedAt(
    @NotNull Instant createdAt) implements SingleValueObject<Instant> {   
        
    public static CreatedAt now() {
        return new CreatedAt(Instant.now());
    }

    @Override
    public Instant value() {
        return createdAt;
    }
}