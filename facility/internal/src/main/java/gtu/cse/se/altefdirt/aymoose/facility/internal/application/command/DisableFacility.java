package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableFacility(
        @NotBlank(message = "Facility ID cannot be null or empty")
        String facilityId
) implements Command {
}
