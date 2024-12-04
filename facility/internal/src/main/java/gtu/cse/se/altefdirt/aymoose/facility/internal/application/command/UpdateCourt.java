package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.time.Instant;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record UpdateCourt(
                String id,
                String name,
                String description,
                Integer height,
                Integer width,
                Integer capacity,
                Integer price,
                List<String> deletedImages,
                List<MultipartFile> newImages) implements Command {
}
