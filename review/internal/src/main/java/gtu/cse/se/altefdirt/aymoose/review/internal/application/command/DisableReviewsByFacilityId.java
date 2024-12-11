package gtu.cse.se.altefdirt.aymoose.review.internal.application.command;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableReviewsByFacilityId(
        @NotBlank(message = "Facility id cannot be null or empty") UUID facilityId) implements Command {
}