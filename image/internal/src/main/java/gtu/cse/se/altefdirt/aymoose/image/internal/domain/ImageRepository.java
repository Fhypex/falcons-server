package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageRepository {
    
    Image save(Image image, MultipartFile file);

    Optional<Image> findById(AggregateId id);

    List<Image> findAllByRelationId(AggregateId relationId);

    String url(Image image);
}
