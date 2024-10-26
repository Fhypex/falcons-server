package gtu.cse.se.altefdirt.aymoose.court.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableCourt(
        @NotBlank(message = "Court ID cannot be null or empty")
        String courtId
) implements Command {
}