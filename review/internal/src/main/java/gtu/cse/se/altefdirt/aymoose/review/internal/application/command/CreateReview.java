package gtu.cse.se.altefdirt.aymoose.review.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateReview(
        @NotBlank(message = "Reservation Id cannot be null or empty")
        String reservationId,

        @NotBlank(message = "User ID cannot be null or empty")
        String userId,

        @NotBlank(message = "Review cannot be null or empty")
        String title,

        @NotBlank(message = "Review cannot be null or empty")
        String content,

        @Min(value = 1, message = "Rating must be between 1 and 5")
        @Max(value = 5, message = "Rating must be between 1 and 5")
        short rating
) implements Command {
}