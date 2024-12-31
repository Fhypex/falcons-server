package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import lombok.Builder;

@Builder
public record FacilityCompressedResponseDTO(
        UUID id,
        String name,
        String description,
        String city,
        String district,
        String rating,
        int reviewCount,
        List<String> imageUrls,
        List<Integer> capacities,
        List<Map<String, Object>> amenities) {
    public static FacilityCompressedResponseDTO from(FacilityView view) {
        return FacilityCompressedResponseDTO.builder()
                .id(view.id())
                .name(view.name())
                .description(view.description())
                .city(view.city())
                .district(view.district())
                .rating(view.rating())
                .reviewCount(view.reviewCount())
                .imageUrls(view.imageUrls())
                .amenities(view.amenities())
                .capacities(view.capacities())
                .build();
    }
}
