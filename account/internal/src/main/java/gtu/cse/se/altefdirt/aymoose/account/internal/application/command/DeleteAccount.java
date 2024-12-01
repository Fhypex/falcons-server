package gtu.cse.se.altefdirt.aymoose.account.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DeleteAccount(@NotBlank String id) implements Command {
}
