package gtu.cse.se.altefdirt.aymoose.facility.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface CourtOperationPort {

    List<AmenityData> findAllAmenities(AggregateId id);

}
