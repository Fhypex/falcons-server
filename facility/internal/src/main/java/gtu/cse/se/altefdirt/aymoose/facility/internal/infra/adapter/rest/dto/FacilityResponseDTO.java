package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.shared.application.CourtData;
import lombok.Builder;

@Builder
public record FacilityResponseDTO(
        String id,
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
        List<Map<String, Object>> amenities,
        List<CourtData> courts) {
    public static FacilityResponseDTO fromView(FacilityView view, List<CourtData> courts) {
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
                .imageUrls(view.imageUrls())
                .amenities(view.amenities())
                .courts(courts)
                .build();
    }
}
