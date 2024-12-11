package gtu.cse.se.altefdirt.aymoose.image.internal.infra.mapper;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.BaseImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageFactory;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa.ImageEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    private final ImageFactory factory;

    public ImageEntity toEntity(Image image) {
        return ImageEntity.builder()
                .id(image.id().value())
                .relationId(image.getRelationId().value())
                .url(image.getUrl())
                .name(image.name())
                .size(image.getSize())
                .extension(image.getExtension())
                .build();
    }

    public ImageEntity toEntityFromBase(BaseImage baseImage, String url) {
        return ImageEntity.builder()
                .id(baseImage.id().value())
                .relationId(baseImage.getRelationId().value())
                .url(url)
                .name(baseImage.name())
                .size(baseImage.getSize())
                .extension(baseImage.getExtension())
                .build();
    }

    public Image toDomain(ImageEntity entity) {
        return factory.load(AggregateId.fromUUID(entity.getId()),
                AggregateId.fromUUID(entity.getRelationId()),
                entity.getName(),
                entity.getSize(),
                entity.getExtension(),
                entity.getUrl());
    }

}
