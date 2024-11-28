package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageOperationPort {

    ImageData find(AggregateId facilityId);

    ImageData save(AggregateId relationId, MultipartFile image);

    int delete(AggregateId imageId);

    List<ImageData> findByRelationId(AggregateId relationId);

    int deleteByRelationId(AggregateId relationId);
}
