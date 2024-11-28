package gtu.cse.se.altefdirt.aymoose.image.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DeleteImage(
                @NotBlank(message = "Id cannot be null or empty") String id) implements Command {
}
