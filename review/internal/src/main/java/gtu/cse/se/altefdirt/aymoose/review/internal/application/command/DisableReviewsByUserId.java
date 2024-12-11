package gtu.cse.se.altefdirt.aymoose.review.internal.application.command;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DisableReviewsByUserId(
        @NotBlank(message = "User ID cannot be null or empty") UUID userId) implements Command {
}