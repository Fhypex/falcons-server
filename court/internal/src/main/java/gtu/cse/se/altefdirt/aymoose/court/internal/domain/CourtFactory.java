package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;

@Component
public class CourtFactory {

    public Court create(AggregateId facilityId,
            CourtDetails courtDetails,
            Measurements measurements,
            Capacity capacity) {
        return new Court(AggregateId.generate(), facilityId, courtDetails, measurements, capacity, true);
    }

    public Court load(AggregateId id,
            AggregateId facilityId,
            CourtDetails courtDetails,
            Measurements measurements,
            Capacity capacity,
            boolean isActive) {
        return new Court(id, facilityId, courtDetails, measurements, capacity,
                isActive);
    }
}
