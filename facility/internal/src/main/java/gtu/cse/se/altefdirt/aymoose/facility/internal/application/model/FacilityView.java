package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;
import lombok.Builder;

@Builder
public record FacilityView(
        UUID id,
        UUID userId,
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
        List<Integer> capacities,
        Integer lowerPriceLimit,
        Integer upperPriceLimit) {
    public FacilityView(Facility facility, List<String> imageUrls, int reviewCount, String rating, String city,
            String district, List<AmenityView> amenities, List<CourtRichData> courts, List<Integer> capacities,
            int lowerPriceLimit,
            int upperPriceLimit) {
        this(facility.id().value(),
                facility.userId().value(),
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
                imageUrls,
                reviewCount,
                rating,
                amenities.stream().map(AmenityView::toMap).toList(),
                courts,
                capacities,
                lowerPriceLimit,
                upperPriceLimit);
    }
}
