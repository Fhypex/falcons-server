package gtu.cse.se.altefdirt.aymoose.image.internal.infra.provider;

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
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
class ImageProviderImpl implements ImageProvider {

    private final ImageRepository imageRepository;

    private ImageData build(Image image) {
        return ImageData.builder()
                .id(image.id())
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
        log.debug("Saving image for relationId {}", relationId);
        Image image = imageRepository.save(relationId, file);
        return build(image);
    }

    public ImageData getImageById(AggregateId id) {
        Image image = imageRepository.findById(id).get();
        return build(image);
    }

    public List<ImageData> getImagesByRelationId(AggregateId relationId) {
        List<Image> images = imageRepository.findByRelationId(relationId);
        return images.stream().map(image -> {
            return build(image);
        }).toList();
    }

    @Override
    public int deleteByRelationId(AggregateId relationId) {
        return imageRepository.deleteByRelationId(relationId);
    }
}
