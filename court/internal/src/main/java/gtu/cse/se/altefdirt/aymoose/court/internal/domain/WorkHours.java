package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import java.time.Instant;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record WorkHours(
    Instant openTime,
    Instant closeTime)
{}

