package gtu.cse.se.altefdirt.aymoose.shared.application;

import lombok.Builder;

@Builder
public record FacilityData(String id,
    String facilityId,
    String name,
    String description,
    Integer height,
    Integer width,
    Integer capacity,
    boolean isActive) {
}
