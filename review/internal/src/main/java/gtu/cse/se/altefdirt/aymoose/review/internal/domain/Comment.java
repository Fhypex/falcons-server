package gtu.cse.se.altefdirt.aymoose.review.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.annotation.ValueObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ValueObject
public record Comment(
                @NotNull @Size(min = 3, max = 80) String title,
                @NotNull @Size(min = 3, max = 255) String content) {
}
