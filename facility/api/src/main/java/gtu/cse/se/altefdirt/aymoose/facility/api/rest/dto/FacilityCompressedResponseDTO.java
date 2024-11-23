package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import lombok.Builder;

@Builder
public record FacilityCompressedResponseDTO(
        String id,
        String name,
        String description,
        String city,
        String district,
        String rating,
        int reviewCount,
        String image,
        List<Map<String, String>> amenities) {
    public static FacilityCompressedResponseDTO from(FacilityView facility) {
        return FacilityCompressedResponseDTO.builder()
                .id(facility.id())
                .name(facility.name())
                .description(facility.description())
                .city(facility.city())
                .district(facility.district())
                .rating(facility.rating())
                .reviewCount(facility.reviewCount())
                .image(facility.imageUrl())
                .image("https://tesiskirala.com/wp-content/uploads/2024/01/IMG-20190515-WA0000.jpg")
                .amenities(List.of(Map.of("name", "Wifi"), Map.of("name", "Parking")))
                .build();
    }
}
