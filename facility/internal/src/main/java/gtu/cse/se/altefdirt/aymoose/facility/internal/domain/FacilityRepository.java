package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;
import java.util.Set;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface FacilityRepository extends Repository<Facility, AggregateId> {

    Set<Long> findUniqueDistricts();

    boolean hasFacilityByDistrictId(Long districtId);

    boolean hasFacilityByDistrictIds(List<Long> districtIds);

    boolean existsByIdAndOwnerId(AggregateId id, AggregateId ownerId);

    int deleteByOwnerId(AggregateId ownerId);
}
