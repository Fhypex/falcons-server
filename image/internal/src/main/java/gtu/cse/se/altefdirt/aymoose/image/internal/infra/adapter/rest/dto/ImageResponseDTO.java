package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.rest.dto;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import lombok.Builder;

@Builder
public record ImageResponseDTO(
        UUID id,
        UUID relationId,
        String url) {
    public static ImageResponseDTO fromDomain(Image image) {
        return ImageResponseDTO.builder()
                .id(image.id().value())
                .relationId(image.getRelationId().value())
                .url(image.url())
                .build();
    }
}