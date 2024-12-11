package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record UpdateCourt(
        UUID id,
        String name,
        String description,
        Integer height,
        Integer width,
        Integer capacity,
        Integer price,
        List<UUID> deletedImages,
        List<MultipartFile> newImages) implements Command {
}
