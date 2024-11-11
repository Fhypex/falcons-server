package gtu.cse.se.altefdirt.aymoose.review.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableReview(
        @NotBlank(message = "User ID cannot be null or empty")
        String reviewId
) implements Command {
}