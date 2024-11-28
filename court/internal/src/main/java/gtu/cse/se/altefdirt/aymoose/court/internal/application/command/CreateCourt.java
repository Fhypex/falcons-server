package gtu.cse.se.altefdirt.aymoose.court.internal.application.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateCourt(
                String ownerId,
                @NotBlank(message = "Facility Id cannot be null or empty") String facilityId,
                @NotBlank(message = "Name cannot be null or empty") String name,
                @NotBlank(message = "Court description can not be null or empty") String description,
                Integer height,
                Integer width,
                Integer capacity,
                Integer price,
                List<MultipartFile> images) implements Command {
}
