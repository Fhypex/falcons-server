package gtu.cse.se.altefdirt.aymoose.review.internal.application.model;

import java.time.Instant;
import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReviewView(
        @NotNull UUID id,
        @NotNull UUID userId,
        @NotNull UUID facilityId,
        @NotNull String author,
        @NotNull Short rating,
        @NotNull String title,
        @NotNull String content,
        @NotNull Instant createdAt,
        @NotNull Instant updatedAt) {

    public ReviewView(Review review, String author) {
        this(review.id().value(), review.getUserId().value(), review.getFacilityId().value(), author,
                review.getRating().leading(), review.comment().title(), review.comment().content(),
                review.createdAt().value(), review.updatedAt().value());
    }
}
