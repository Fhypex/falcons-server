package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import java.util.Map;

public record AmenityData(String name, String imageUrl) {

    public Map<String, Object> toMap() {
        return Map.of("name", name, "imageUrl", imageUrl);
    }
}
