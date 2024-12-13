package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import lombok.Builder;

@Builder
public record ReviewResponseWithAuthorDTO(
        UUID id,
        String author,
        short rating,
        String title,
        String content,
        String createdAt,
        String updatedAt) {
    public static ReviewResponseWithAuthorDTO fromView(ReviewView reviewView) {
        return new ReviewResponseWithAuthorDTO(reviewView.id(),
                reviewView.author(),
                reviewView.rating().shortValue(),
                reviewView.title(),
                reviewView.content(),
                reviewView.createdAt().toString(),
                reviewView.updatedAt().toString());
    }
}