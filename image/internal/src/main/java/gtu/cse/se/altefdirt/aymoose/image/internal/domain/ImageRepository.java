package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import java.util.List;
import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageRepository {
    
    Image save(Image Image);

    Optional<Image> findById(AggregateId id);

    List<Image> findAll();

}
