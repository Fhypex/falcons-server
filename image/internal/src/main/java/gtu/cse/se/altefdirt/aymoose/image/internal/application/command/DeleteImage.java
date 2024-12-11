package gtu.cse.se.altefdirt.aymoose.image.internal.application.command;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DeleteImage(
        @NotBlank(message = "Id cannot be null or empty") UUID id) implements Command {
}
