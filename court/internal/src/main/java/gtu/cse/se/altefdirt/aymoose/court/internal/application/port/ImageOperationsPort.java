package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageOperationsPort {
    
    List<ImageData> findAll(AggregateId courtId);

    ImageData save(String image, String relationId, String title);

    void delete(AggregateId imageId);
}
