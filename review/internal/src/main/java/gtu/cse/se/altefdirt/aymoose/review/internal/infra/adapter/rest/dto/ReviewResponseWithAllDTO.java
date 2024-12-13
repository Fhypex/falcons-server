package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import lombok.Builder;

@Builder
public record ReviewResponseWithAllDTO(
        UUID id,
        short rating,
        String title,
        String content,
        String createdAt,
        String updatedAt,
        String author,
        String profilePicture,
        String facilityName) {

    public static ReviewResponseWithAllDTO fromView(ReviewView reviewView) {
        return new ReviewResponseWithAllDTO(reviewView.id(),
                reviewView.rating().shortValue(),
                reviewView.title(),
                reviewView.content(),
                reviewView.createdAt().toString(),
                reviewView.updatedAt().toString(),
                reviewView.author(),
                reviewView.profilePicture(),
                reviewView.facilityName());
    }
}