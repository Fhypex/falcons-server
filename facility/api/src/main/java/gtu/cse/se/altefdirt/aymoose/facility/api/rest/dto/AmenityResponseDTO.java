package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import lombok.Builder;

@Builder
public record AmenityResponseDTO(
        String id,
        String name,
        String image) {
    public static AmenityResponseDTO fromView(AmenityView view) {
        return AmenityResponseDTO.builder()
                .id(view.id())
                .name(view.name())
                .image(view.imageUrl())
                .build();
    }
}
