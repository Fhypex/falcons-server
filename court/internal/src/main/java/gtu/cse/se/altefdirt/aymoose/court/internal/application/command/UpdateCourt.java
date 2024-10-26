package gtu.cse.se.altefdirt.aymoose.court.internal.application.command;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record UpdateCourt(
        @NotBlank(message = "Court Id cannot be null or empty")
        String id,

        @NotBlank(message = "Name cannot be null or empty")
        String name,

        @NotBlank(message = "Court description can not be null or empty")
        String description,

        Integer capacity,

        Integer height,

        Integer width,

        Instant openTime,

        Instant closeTime,

        Double latitude,

        Double longitude,

        List<String> amenityIds
) implements Command {
}
