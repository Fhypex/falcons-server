package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;
import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface FacilityRepository {

    Facility save(Facility facility);

    Optional<Facility> findById(AggregateId id);

    List<Facility> findAll();

}
