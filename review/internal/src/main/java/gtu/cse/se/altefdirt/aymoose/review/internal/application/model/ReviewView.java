package gtu.cse.se.altefdirt.aymoose.review.internal.application.model;

import java.time.Instant;
import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.shared.application.UserData;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ReviewView(
        @NotNull UUID id,
        @NotNull UUID userId,
        @NotNull UUID facilityId,
        @NotNull String author,
        @NotNull String profilePicture,
        @NotNull Short rating,
        @NotNull String title,
        @NotNull String content,
        @NotNull Instant createdAt,
        @NotNull Instant updatedAt,
        String facilityName) {

    public ReviewView(Review review, UserData author, String facilityName) {
        this(review.id().value(), review.getUserId().value(), review.getFacilityId().value(), author.fullName().value(),
                author.imageUrl(),
                review.getRating().leading(), review.comment().title(), review.comment().content(),
                review.createdAt().value(), review.updatedAt().value(), facilityName);
    }
}
