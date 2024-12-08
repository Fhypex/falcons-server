package gtu.cse.se.altefdirt.aymoose.account.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.validation.constraints.NotBlank;

public record CreateAccount(@NotBlank AggregateId id,
                @NotBlank String firstName, @NotBlank String lastName) implements Command {
}
