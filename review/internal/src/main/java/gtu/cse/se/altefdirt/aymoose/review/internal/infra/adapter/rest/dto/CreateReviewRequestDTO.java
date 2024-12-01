package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto;

import org.apache.commons.lang3.Validate;
import org.springframework.validation.annotation.Validated;


public record CreateReviewRequestDTO(
    String reservationId,
    String userId,
    String title, 
    String content,
    Short rating
) {

    public CreateReviewRequestDTO(
        String reservationId,
        String userId,
        String title,
        String content,
        Short rating) 
        {   
        Validate.notNull(reservationId, "Reservation ID cannot be null");
        Validate.notNull(userId, "User ID cannot be null");
        Validate.notNull(title, "Review title cannot be null");
        Validate.notNull(content, "Review content cannot be null");
        Validate.notNull(rating, "Rating cannot be null");
        Validate.isTrue(rating >= 1 && rating <= 5, "Rating must be between 1 and 5");
        Validate.isTrue(reservationId.length() == 36, "Invalid reservation ID");
        Validate.isTrue(userId.length() == 36, "Invalid user ID");
        Validate.isTrue(title.length() >= 3 && title.length() <= 80, "Review title must be between 3 and 80 characters");
        Validate.isTrue(content.length() >= 3 && content.length() <= 200, "Review content must be between 3 and 200 characters");
        this.reservationId = reservationId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}