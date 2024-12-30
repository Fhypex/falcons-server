package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.rest.dto;

import org.apache.commons.lang3.Validate;

public record UpdateReviewRequestDTO(
                String title,
                String content,
                Short rating) {

        public UpdateReviewRequestDTO(
                        String title,
                        String content,
                        Short rating) {
                Validate.notNull(title, "Review title cannot be null");
                Validate.notNull(content, "Review content cannot be null");
                Validate.notNull(rating, "Rating cannot be null");
                Validate.isTrue(rating >= 1 && rating <= 5, "Rating must be between 1 and 5");
                Validate.isTrue(title.length() >= 3 && title.length() <= 80,
                                "Review title must be between 3 and 80 characters");
                Validate.isTrue(content.length() >= 3 && content.length() <= 200,
                                "Review content must be between 3 and 200 characters");
                this.title = title;
                this.content = content;
                this.rating = rating;
        }
}