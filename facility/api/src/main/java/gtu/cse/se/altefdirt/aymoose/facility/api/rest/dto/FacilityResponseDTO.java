package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
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
        String image,
        String rating,
        int reviewCount,
         List<Map<String, Object>> amenities) {
    public static FacilityResponseDTO fromView(FacilityView view) {
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
                .image(view.imageUrl())
                .amenities(view.amenities())
                .build();
    }
}
