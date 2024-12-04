package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import java.time.LocalDate;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.validation.constraints.NotBlank;

public record CreateReservation(
                @NotBlank(message = "Facility name cannot be null or empty") AggregateId userId,
                @NotBlank(message = "Phone number cannot be null or empty") String courtId,
                @NotBlank(message = "Facility name cannot be null or empty") LocalDate date,
                @NotBlank(message = "Location cannot be null or empty") int hour) implements Command {
}
