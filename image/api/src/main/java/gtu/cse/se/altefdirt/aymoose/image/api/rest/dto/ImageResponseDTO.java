package gtu.cse.se.altefdirt.aymoose.image.api.rest.dto;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
import lombok.Builder;

@Builder
public record ImageResponseDTO(
    String id,
    String relationId,
    MultipartFile file,
    String extension
)
{
    public static ImageResponseDTO fromView(ImageView imageView) {
        return ImageResponseDTO.builder()
            .id(imageView.id())
            .relationId(imageView.relationId())
            .file(imageView.file())
            .extension(imageView.extension())
            .build();
    }
}