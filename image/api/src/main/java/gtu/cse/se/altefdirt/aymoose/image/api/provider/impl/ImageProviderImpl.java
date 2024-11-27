package gtu.cse.se.altefdirt.aymoose.image.api.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.api.provider.ImageProvider;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
class ImageProviderImpl implements ImageProvider {

    private final ImageRepository imageRepository;

    private ImageData build(Image image) {
        return ImageData.builder()
                .id(image.id().value())
                .url(image.url())
                .size(image.size())
                .extension(image.extension())
                .build();
    }

    @Override
    public int deleteById(AggregateId id) {
        return imageRepository.deleteById(id);
    }

    @Override
    public ImageData save(AggregateId relationId, MultipartFile file) {
        Image image = imageRepository.save(relationId, file);
        return build(image);
    }

    public ImageData getImageById(AggregateId id) {
        Image image = imageRepository.findById(id).get();
        return build(image);
    }

    public List<ImageData> getImagesByRelationId(AggregateId relationId) {
        List<Image> images = imageRepository.findAllByRelationId(relationId);
        return images.stream().map(image -> {
            return build(image);
        }).collect(Collectors.toUnmodifiableList());
    }
}
