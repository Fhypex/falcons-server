package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record DeleteCourt(
                UUID id) implements Command {
}
