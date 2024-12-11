package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateCourt(
        UUID userId,
        @NotBlank(message = "Facility Id cannot be null or empty") UUID facilityId,
        @NotBlank(message = "Name cannot be null or empty") String name,
        @NotBlank(message = "Court description can not be null or empty") String description,
        Integer height,
        Integer width,
        Integer capacity,
        Integer price,
        List<MultipartFile> images) implements Command {
}
