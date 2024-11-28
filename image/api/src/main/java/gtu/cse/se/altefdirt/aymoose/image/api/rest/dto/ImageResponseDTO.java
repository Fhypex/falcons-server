package gtu.cse.se.altefdirt.aymoose.image.api.rest.dto;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import lombok.Builder;

@Builder
public record ImageResponseDTO(
        String id,
        String relationId,
        String url) {
    public static ImageResponseDTO fromDomain(Image image) {
        return ImageResponseDTO.builder()
                .id(image.id().value())
                .relationId(image.getRelationId().value())
                .url(image.url())
                .build();
    }
}