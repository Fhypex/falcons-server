package gtu.cse.se.altefdirt.aymoose.review.internal.application.command;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableReview(
                @NotBlank(message = "Review ID cannot be null or empty") UUID reviewId) implements Command {
}