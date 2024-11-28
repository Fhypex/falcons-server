package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import lombok.Builder;

@Builder
public record AmenityView(
        String id,
        String name,
        String imageUrl) {
    public AmenityView(Amenity amenity, String imageUrl) {
        this(amenity.id().value(),
                amenity.name(),
                imageUrl);
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "id", id,
                "name", name,
                "imageUrl", imageUrl);
    }
}
