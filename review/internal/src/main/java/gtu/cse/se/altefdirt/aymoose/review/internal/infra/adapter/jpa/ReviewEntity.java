package gtu.cse.se.altefdirt.aymoose.review.internal.infra.adapter.jpa;

import java.time.Instant;
import java.util.UUID;
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
    private UUID id;
    private UUID reservationId;
    private UUID userId;
    private UUID facilityId;
    private short rating;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean needsModeration;
    private boolean disabled;
}
