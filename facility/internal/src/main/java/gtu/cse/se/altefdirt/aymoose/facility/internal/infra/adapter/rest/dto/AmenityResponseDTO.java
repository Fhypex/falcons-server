package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import lombok.Builder;

@Builder
public record AmenityResponseDTO(
        UUID id,
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
