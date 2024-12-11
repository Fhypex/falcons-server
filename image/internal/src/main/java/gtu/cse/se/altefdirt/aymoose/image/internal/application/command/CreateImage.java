package gtu.cse.se.altefdirt.aymoose.image.internal.application.command;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateImage(
                @NotBlank(message = "Relation Id cannot be null or empty") UUID relationId,
                MultipartFile file) implements Command {
}
