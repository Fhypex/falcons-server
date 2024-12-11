package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record CreateAmenity(
        MultipartFile image,
        String name) implements Command {
}
