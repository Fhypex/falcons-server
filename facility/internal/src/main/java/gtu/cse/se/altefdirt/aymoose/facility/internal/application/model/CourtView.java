package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import java.util.List;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import lombok.Builder;

@Builder
public record CourtView(
        UUID id,
        UUID facilityId,
        String name,
        String description,
        Integer height,
        Integer width,
        Integer capacity,
        Integer price,
        List<String> imageUrls) {
    public CourtView(Court court, List<String> imageUrls) {
        this(court.id().value(),
                court.facilityId().value(),
                court.details().name(),
                court.details().description(),
                court.measurements().height(),
                court.measurements().width(),
                court.capacity().value(),
                court.price().value(),
                imageUrls);
    }
}
