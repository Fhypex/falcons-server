package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;

@Component
public class CourtFactory {

    public Court create(
            AggregateId ownerId,
            AggregateId facilityId,
            CourtDetails courtDetails,
            Measurements measurements,
            Capacity capacity) {
        return new Court(AggregateId.generate(), ownerId, facilityId, courtDetails, measurements, capacity);
    }

    public Court load(AggregateId id,
            AggregateId ownerId,
            AggregateId facilityId,
            CourtDetails courtDetails,
            Measurements measurements,
            Capacity capacity) {
        return new Court(id, ownerId, facilityId, courtDetails, measurements, capacity);
    }
}
