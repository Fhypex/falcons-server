package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.image.api.provider.ImageProvider;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
class ImageOperationAdapter implements ImageOperationPort {

    private final ImageProvider imageProvider;

    @Override
    public ImageData findById(AggregateId id) {
        return imageProvider.getImageById(id);
    }

    @Override
    public Optional<ImageData> findOneByRelationId(AggregateId relationId) {
        List<ImageData> images = imageProvider.getImagesByRelationId(relationId);
        return images.isEmpty() ? Optional.empty() : Optional.of(images.get(0));
    }

    @Override
    public ImageData save(AggregateId relationId, MultipartFile image) {
        log.debug("Saving image for relationId {}", relationId);
        return imageProvider.save(relationId, image);
    }

    @Override
    public int delete(AggregateId imageId) {
        return imageProvider.deleteById(imageId);
    }

    @Override
    public List<ImageData> findByRelationId(AggregateId relationId) {
        return imageProvider.getImagesByRelationId(relationId);
    }

    @Override
    public int deleteByRelationId(AggregateId relationId) {
        return imageProvider.deleteByRelationId(relationId);
    }
}
