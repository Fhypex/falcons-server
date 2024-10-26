package gtu.cse.se.altefdirt.aymoose.court.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableCourtsByFacilityId(
        @NotBlank(message = "Facility id cannot be null or empty")
        String facilityId
) implements Command {
}