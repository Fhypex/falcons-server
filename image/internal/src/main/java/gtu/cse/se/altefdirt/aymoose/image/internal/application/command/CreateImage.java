package gtu.cse.se.altefdirt.aymoose.image.internal.application.command;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateImage(
        @NotBlank(message = "Relation Id cannot be null or empty")
        String relationId,        
        MultipartFile file
) implements Command {
}
