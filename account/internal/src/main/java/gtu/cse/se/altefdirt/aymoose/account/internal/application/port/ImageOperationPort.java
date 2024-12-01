package gtu.cse.se.altefdirt.aymoose.account.internal.application.port;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageOperationPort {

    ImageData findById(AggregateId relationId);

    ImageData save(AggregateId relationId, MultipartFile image);

    int delete(AggregateId imageId);

    Optional<ImageData> findOneByRelationId(AggregateId relationId);
    
    List<ImageData> findByRelationId(AggregateId relationId);

    int deleteByRelationId(AggregateId relationId);
}
