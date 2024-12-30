package gtu.cse.se.altefdirt.aymoose.image.api.provider;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageProvider {

    int deleteById(AggregateId id);

    ImageData save(AggregateId relationId, MultipartFile file);

    ImageData getImageById(AggregateId id);

    List<ImageData> getImagesByRelationId(AggregateId relationId);

    int deleteByRelationId(AggregateId relationId);

    int deleteByRelationIds(List<AggregateId> relationIds);
}
