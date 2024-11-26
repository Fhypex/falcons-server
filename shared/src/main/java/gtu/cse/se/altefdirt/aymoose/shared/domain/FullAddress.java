package gtu.cse.se.altefdirt.aymoose.shared.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FullAddress(
                @NotNull @Min(1) @Max(500) String address) {
}