package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Measurements(
    @NotNull
    @Min(1)
    @Max(400)
    Integer width, 
    
    @NotNull
    @Min(1)
    @Max(400)
    Integer height)
{}
