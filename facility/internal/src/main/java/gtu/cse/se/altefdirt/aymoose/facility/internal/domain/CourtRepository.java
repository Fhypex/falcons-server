package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface CourtRepository extends Repository<Court, AggregateId> {

    boolean existsByIdAndOwnerId(AggregateId id, AggregateId userId);

    int deleteByFacilityId(AggregateId facilityId);
    
    List<Court> findByFacilityId(AggregateId facilityId);

}
