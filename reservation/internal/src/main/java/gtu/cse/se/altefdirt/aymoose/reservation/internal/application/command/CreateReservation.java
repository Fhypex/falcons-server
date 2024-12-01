package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateReservation(
        @NotBlank(message = "Owner Id cannot be null or empty") String userId,
        @NotBlank(message = "Phone number cannot be null or empty") String courtId,
        @NotBlank(message = "Facility name cannot be null or empty") String date,
        @NotBlank(message = "Location cannot be null or empty") int hour) implements Command {
}
