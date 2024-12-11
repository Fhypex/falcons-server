package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableFacility(
                @NotBlank(message = "Facility ID cannot be null or empty") UUID facilityId) implements Command {
}
