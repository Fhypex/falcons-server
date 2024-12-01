package gtu.cse.se.altefdirt.aymoose.account.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateAccount(@NotBlank String mailAddress, @NotBlank String password, 
                @NotBlank String firstName, @NotBlank String lastName) implements Command {
}
