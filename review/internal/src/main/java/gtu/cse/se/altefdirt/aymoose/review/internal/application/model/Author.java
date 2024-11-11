package gtu.cse.se.altefdirt.aymoose.review.internal.application.model;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.FullName;
import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValueObject;
import jakarta.validation.constraints.NotNull;

@ValueObject
public record Author(
    @NotNull AggregateId id,
    @NotNull FullName fullName) {    
}
