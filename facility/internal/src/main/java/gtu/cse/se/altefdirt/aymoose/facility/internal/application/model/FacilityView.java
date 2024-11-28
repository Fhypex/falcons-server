package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
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
        List<String> imageUrls,
        int reviewCount,
        String rating,
        List<Map<String, Object>> amenities,
        List<CourtRichData> courts,
        Integer lowerPriceLimit,
        Integer upperPriceLimit) {
    public FacilityView(Facility facility, List<String> images, int reviewCount, String rating, String city,
            String district, List<AmenityView> amenities, List<CourtRichData> courts, int lowerPriceLimit,
            int upperPriceLimit) {
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
                images,
                reviewCount,
                rating,
                amenities.stream().map(AmenityView::toMap).toList(),
                courts,
                lowerPriceLimit,
                upperPriceLimit);
    }
}
