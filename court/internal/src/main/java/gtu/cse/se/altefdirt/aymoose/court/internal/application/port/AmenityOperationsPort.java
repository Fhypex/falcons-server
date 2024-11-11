package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.AmenityData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface AmenityOperationsPort {

    AmenityData get(AggregateId id);

    boolean exists(List<String> ids);
    
    List<AmenityData> findAll(List<AggregateId> ids);
}
