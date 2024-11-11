package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;

@Component
public class FacilityFactory {

    public Facility create(AggregateId userId,
                           String facilityName,
                           String phoneNumber,
                           String facilityDescription,
                           Location location,
                           String contactDetails,
                           FacilityCapacity courtCount) {
        return new Facility(AggregateId.generate(), userId, facilityName, phoneNumber, facilityDescription, location, contactDetails, courtCount, true);
    }

    public Facility load(AggregateId id,
                         AggregateId userId,
                         String facilityName,
                         String phoneNumber,
                         String facilityDescription,
                         Location location,
                         String contactDetails,
                         FacilityCapacity courtCount,
                         boolean isActive) {
        return new Facility(id, userId, facilityName, phoneNumber, facilityDescription, location, contactDetails, courtCount, isActive);
    }
}
