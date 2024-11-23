package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.City;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;

@Component
public class FacilityFactory {

    public Facility create(AggregateId userId,
            PhoneNumber phoneNumber,
            String facilityName,
            String facilityDescription,
            Location location,
            City city,
            String contactDetails,
            boolean isActive) {
        return new Facility(AggregateId.generate(), userId, phoneNumber, facilityName, facilityDescription, location,
                city, contactDetails, isActive);
    }

    public Facility load(AggregateId id,
            AggregateId userId,
            PhoneNumber phoneNumber,
            String facilityName,
            String facilityDescription,
            Location location,
            City city,
            String contactDetails,
            boolean isActive) {
        return new Facility(id, userId, phoneNumber, facilityName, facilityDescription, location,
                city, contactDetails, isActive);
    }
}
