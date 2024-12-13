package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import lombok.Builder;

@Builder
public record ReviewResponseWithFacilityNameDTO(
        UUID id,
        short rating,
        String title,
        String content,
        String createdAt,
        String updatedAt,
        String facilityName) {

    public static ReviewResponseWithFacilityNameDTO fromView(ReviewView reviewView) {
        return new ReviewResponseWithFacilityNameDTO(reviewView.id(),
                reviewView.rating().shortValue(),
                reviewView.title(),
                reviewView.content(),
                reviewView.createdAt().toString(),
                reviewView.updatedAt().toString(),
                reviewView.facilityName());
    }
}