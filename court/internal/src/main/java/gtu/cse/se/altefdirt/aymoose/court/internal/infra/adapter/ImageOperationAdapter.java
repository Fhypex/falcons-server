package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.image.api.provider.ImageProvider;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
class ImageOperationAdapter implements ImageOperationPort{

    private final ImageProvider imageProvider;

    @Override
    public ImageData find(AggregateId facilityId) {
        return imageProvider.getImageById(facilityId);
    }

    @Override
    public ImageData save(AggregateId relationId, MultipartFile image) {
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
