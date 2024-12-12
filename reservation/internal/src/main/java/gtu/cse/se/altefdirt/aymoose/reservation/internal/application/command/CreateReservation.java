package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import jakarta.validation.constraints.NotBlank;

public record CreateReservation(
                @NotBlank(message = "Facility name cannot be null or empty") AggregateId userId,
                @NotBlank(message = "Phone number cannot be null or empty") UUID courtId,
                @NotBlank(message = "Facility name cannot be null or empty") Date date,
                @NotBlank(message = "Location cannot be null or empty") Integer hour) implements Command {
}
