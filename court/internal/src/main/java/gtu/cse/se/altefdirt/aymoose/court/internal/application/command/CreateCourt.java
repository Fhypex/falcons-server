package gtu.cse.se.altefdirt.aymoose.court.internal.application.command;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateCourt(
        @NotBlank(message = "Facility Id cannot be null or empty")
        String facilityId,
        @NotBlank(message = "Name cannot be null or empty")
        String name,
        @NotBlank(message = "Court description can not be null or empty")
        String description,
        Integer height,
        Integer width,
        Integer capacity,
        Instant openTime,
        Instant closeTime,
        Double latitude,
        Double longitude,
        List<String> amenityIds,
        List<String> images
) implements Command {
}