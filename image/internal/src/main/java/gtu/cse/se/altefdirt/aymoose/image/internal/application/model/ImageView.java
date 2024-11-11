package gtu.cse.se.altefdirt.aymoose.image.internal.application.model;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import lombok.Builder;

@Builder
public record ImageView (
    String id,
    String relationId,
    MultipartFile file,
    String extension
   
)
{
    public ImageView (Image image) {
        this(image.id().value(), image.getRelationId().value() , image.getFile(), image.getExtension());
    }
}
