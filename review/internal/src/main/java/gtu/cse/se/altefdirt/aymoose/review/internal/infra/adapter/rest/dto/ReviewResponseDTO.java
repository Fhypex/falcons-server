package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto;

import gtu.cse.se.altefdirt.aymoose.review.internal.application.model.ReviewView;
import lombok.Builder;

@Builder
public record ReviewResponseDTO(
    String id,
    String author,
    short rating,
    String title,
    String content,
    String createdAt,
    String updatedAt
)
{
    public static ReviewResponseDTO fromView(ReviewView reviewView) {
        return new ReviewResponseDTO(reviewView.id(),
                                     reviewView.author(),
                                     reviewView.rating(),
                                     reviewView.title(), 
                                     reviewView.content(),
                                     reviewView.createdAt().toString(),
                                     reviewView.updatedAt().toString());
    }
}