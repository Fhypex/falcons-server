package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;
import java.util.Optional;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface AmenityRepository {

    Amenity save(Amenity facility);

    Optional<Amenity> findById(AggregateId id);

    List<Amenity> findAll();

    List<Amenity> findAll(List<AggregateId> ids);

    boolean existsByIdIn(List<AggregateId> ids);

    boolean exists(AggregateId id);

}
