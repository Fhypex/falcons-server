package gtu.cse.se.altefdirt.aymoose.review.internal.infra.mapper;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Comment;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Rating;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.Review;
import gtu.cse.se.altefdirt.aymoose.review.internal.domain.ReviewFactory;
import gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa.ReviewEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.CreatedAt;
import gtu.cse.se.altefdirt.aymoose.shared.domain.UpdatedAt;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    private final ReviewFactory factory;

    public ReviewEntity toEntity(Review review) {
        return ReviewEntity.builder()
                .id(review.id().value())
                .reservationId(review.getReservationId().value())
                .userId(review.getUserId().value())
                .facilityId(review.getFacilityId().value())
                .rating(review.getRating().leading())
                .title(review.comment().title())
                .content(review.comment().content())
                .createdAt(review.createdAt().value())
                .updatedAt(review.updatedAt().value())
                .needsModeration(review.isNeedsModeration())
                .disabled(review.isDisabled())
                .build();
    }

    public Review toDomain(ReviewEntity entity) {
        return factory.load(
                AggregateId.fromUUID(entity.getId()),
                AggregateId.fromUUID(entity.getReservationId()),
                AggregateId.fromUUID(entity.getUserId()),
                AggregateId.fromUUID(entity.getFacilityId()),
                new Comment(entity.getTitle(), entity.getContent()),
                Rating.fromShort(entity.getRating()),
                new CreatedAt(entity.getCreatedAt()),
                new UpdatedAt(entity.getUpdatedAt()),
                entity.isNeedsModeration(),
                entity.isDisabled());
    }
}
