package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

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

        String location,

        List<String> amenityIds
) implements Command {
}
