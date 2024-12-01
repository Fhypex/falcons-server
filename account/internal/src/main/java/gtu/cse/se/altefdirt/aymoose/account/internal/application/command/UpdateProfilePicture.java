package gtu.cse.se.altefdirt.aymoose.account.internal.application.command;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public record UpdateProfilePicture(AggregateId userId, MultipartFile image) implements Command {
}
