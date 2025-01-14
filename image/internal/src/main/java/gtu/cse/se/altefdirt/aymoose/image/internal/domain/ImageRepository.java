package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageRepository {

    Image save(BaseImage baseImage, MultipartFile file);

    Image save(AggregateId relationId, MultipartFile file);

    Optional<Image> findById(AggregateId id);

    List<Image> findByRelationId(AggregateId relationId);

    Integer deleteById(AggregateId id);

    Integer deleteByRelationId(AggregateId relationId);

    Integer deleteByRelationIds(List<AggregateId> relationIds);
}
