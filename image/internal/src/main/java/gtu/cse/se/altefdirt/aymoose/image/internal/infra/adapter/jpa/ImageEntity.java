package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.BaseImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ImageEntity {

    @Id
    private String id;
    private String relationId;

    @Column(name = "url", length = 512)
    private String url;
    private String name;
    private Long size;
    private String extension;

    public static ImageEntity from(Image image) {
        return ImageEntity.builder()
                .id(image.id().value())
                .relationId(image.getRelationId().value())
                .url(image.getUrl())
                .name(image.name())
                .size(image.getSize())
                .extension(image.getExtension())
                .build();
    }

    public static ImageEntity fromBase(BaseImage baseImage, String url) {
        return ImageEntity.builder()
                .id(baseImage.id().value())
                .relationId(baseImage.getRelationId().value())
                .url(url)
                .name(baseImage.name())
                .size(baseImage.getSize())
                .extension(baseImage.getExtension())
                .build();
    }
}
