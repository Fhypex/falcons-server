package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FacilityCapacity(
    @NotNull
    @Min(1)
    @Max(50) 
    Integer value
) {}
