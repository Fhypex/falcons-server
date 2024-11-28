package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface CourtRepository extends Repository<Court, AggregateId> {

    int deleteByFacilityId(AggregateId facilityId);
    
    List<Court> findByFacilityId(AggregateId facilityId);

}
