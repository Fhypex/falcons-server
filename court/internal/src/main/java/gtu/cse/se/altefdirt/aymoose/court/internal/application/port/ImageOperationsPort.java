package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageOperationsPort {
    
    List<ImageData> findAll(AggregateId courtId);

    ImageData save(MultipartFile image, String relationId);

    void delete(AggregateId imageId);
}
