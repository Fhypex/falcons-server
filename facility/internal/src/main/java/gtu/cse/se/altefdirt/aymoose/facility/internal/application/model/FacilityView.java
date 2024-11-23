package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import lombok.Builder;

@Builder
public record FacilityView(
        String id,
        String ownerId,
        String phoneNumber,
        String name,
        String description,
        String location,
        String city,
        String district,
        String fullAddress,
        String contactDetails,
        boolean isActive,
        String imageUrl,
        int reviewCount,
        String rating,
        List<Map<String, Object>> reviews) {
    public FacilityView(Facility facility, String imageUrl, int reviewCount, String rating, List<AmenityData> reviews) {
        this(facility.id().value(),
                facility.ownerId().value(),
                facility.phoneNumber().value(),
                facility.name(),
                facility.description(),
                facility.location().value(),
                facility.address().city(),
                facility.address().district(),
                facility.address().fullAddress(),
                facility.contactDetails(),
                facility.isActive(),
                imageUrl,
                reviewCount,
                rating,
                reviews.stream().map(AmenityData::toMap).toList());
    }
}
