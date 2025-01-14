package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtRichData;
import lombok.Builder;

@Builder
public record FacilityResponseDTO(
        UUID id,
        String phoneNumber,
        String name,
        String description,
        String city,
        String district,
        String fullAddress,
        String location,
        String contactDetails,
        int openTime,
        int closeTime,
        List<String> imageUrls,
        String rating,
        int reviewCount,
        List<Integer> capacities,
        int lowerPriceLimit,
        int upperPriceLimit,
        List<Map<String, Object>> amenities,
        List<CourtRichData> courts,
        Boolean isActive) {

    public static FacilityResponseDTO richened(FacilityView view) {

        return FacilityResponseDTO.builder()
                .id(view.id())
                .phoneNumber(view.phoneNumber())
                .name(view.name())
                .description(view.description())
                .city(view.city())
                .district(view.district())
                .fullAddress(view.fullAddress())
                .location(view.location())
                .contactDetails(view.contactDetails())
                .openTime(view.openTime())
                .closeTime(view.closeTime())
                .rating(view.rating())
                .reviewCount(view.reviewCount())
                .capacities(view.capacities())
                .lowerPriceLimit(view.lowerPriceLimit())
                .upperPriceLimit(view.upperPriceLimit())
                .imageUrls(view.imageUrls())
                .amenities(view.amenities())
                .courts(view.courts())
                .isActive(view.isActive())
                .build();
    }
}
