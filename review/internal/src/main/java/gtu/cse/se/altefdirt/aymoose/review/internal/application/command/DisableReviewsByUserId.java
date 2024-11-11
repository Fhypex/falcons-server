package gtu.cse.se.altefdirt.aymoose.review.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record DisableReviewsByUserId(
        @NotBlank(message = "User ID cannot be null or empty")
        String userId
) implements Command {
}