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
        int openTime,
        int closeTime,
        boolean isActive,
        String imageUrl,
        int reviewCount,
        String rating,
        List<Map<String, Object>> amenities) {
    public FacilityView(Facility facility, String imageUrl, int reviewCount, String rating, String city, String district, List<AmenityView> amenities) {
        this(facility.id().value(),
                facility.ownerId().value(),
                facility.phoneNumber().value(),
                facility.name(),
                facility.description(),
                facility.location().value(),
                city,
                district,
                facility.address().fullAddress(),
                facility.contactDetails(),
                facility.workHours().openTime(),
                facility.workHours().closeTime(),
                facility.isActive(),
                imageUrl,
                reviewCount,
                rating,
                amenities.stream().map(AmenityView::toMap).toList());
    }
}
