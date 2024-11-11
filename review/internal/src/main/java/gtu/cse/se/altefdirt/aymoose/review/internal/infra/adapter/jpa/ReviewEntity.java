package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa;

import java.time.Instant;

import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Comment;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Rating;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.UpdatedAt;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ReviewEntity {
    
    @Id
    private String id;
    private String reservationId;
    private String userId;
    private String facilityId;
    private short rating;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean needsModeration;
    private boolean disabled;


    public static ReviewEntity fromDomain(Review review) {
        return ReviewEntity.builder()
                .id(review.id().value())
                .reservationId(review.getReservationId().value())
                .userId(review.getUserId().value())
                .facilityId(review.getFacilityId().value())
                .rating(review.getRating().value())
                .title(review.comment().title())
                .content(review.comment().content())
                .createdAt(review.createdAt().value())
                .updatedAt(review.updatedAt().value())
                .needsModeration(review.isNeedsModeration())
                .disabled(review.isDisabled())
                .build();
    }

    public Review toDomain(ReviewFactory factory) {
        return factory.load(
                AggregateId.from(id),
                AggregateId.from(reservationId),
                AggregateId.from(userId),
                AggregateId.from(facilityId),
                new Comment(title, content),
                new Rating(rating),
                new CreatedAt(createdAt),
                new UpdatedAt(updatedAt),
                needsModeration,
                disabled
        );
    }

}
