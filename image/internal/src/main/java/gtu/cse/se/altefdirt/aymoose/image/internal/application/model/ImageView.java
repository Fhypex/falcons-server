package gtu.cse.se.altefdirt.aymoose.image.internal.application.model;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import lombok.Builder;

@Builder
public record ImageView (
    String id,
    String relationId,
    String url,
    String name,
    String extension
   
)
{
    public ImageView (Image image, String url) {
        this(image.id().value(), image.getRelationId().value(), url, image.getName(), image.getExtension());
    }
}
