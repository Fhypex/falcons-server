package gtu.cse.se.altefdirt.aymoose.image.api.rest.dto;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
import lombok.Builder;

@Builder
public record ImageResponseDTO(
    String id,
    String relationId,
    String url
)
{
    public static ImageResponseDTO fromView(ImageView imageView) {
        return ImageResponseDTO.builder()
            .id(imageView.id())
            .relationId(imageView.relationId())
            .url(imageView.url())
            .build();
    }
}