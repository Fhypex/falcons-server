package gtu.cse.se.altefdirt.aymoose.facility.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageOperationsPort {
    
    List<ImageData> findAll(AggregateId facilityId);

    ImageData save(String image, String relationId, String title);

    void delete(AggregateId imageId);
}

