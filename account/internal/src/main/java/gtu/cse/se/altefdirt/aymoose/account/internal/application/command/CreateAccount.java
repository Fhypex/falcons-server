package gtu.cse.se.altefdirt.aymoose.account.internal.application.command;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateAccount(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String mailAddress,
        @NotBlank String fullName
) implements Command {
}
