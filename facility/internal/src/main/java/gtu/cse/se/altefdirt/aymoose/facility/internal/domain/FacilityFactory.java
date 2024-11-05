package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
public class FacilityFactory {

    public Facility create(AggregateId userId,
                           String facilityName,
                           String phoneNumber,
                           String facilityDescription,
                           String location,
                           String city,
                           String district,
                           String contactDetails,
                           FacilityCapacity courtCount) {
        return new Facility(AggregateId.generate(), userId, facilityName, phoneNumber, facilityDescription, location, city, district, contactDetails, courtCount, true);
    }

    public Facility load(AggregateId id,
                         AggregateId userId,
                         String facilityName,
                         String phoneNumber,
                         String facilityDescription,
                         String location,
                         String city,
                         String district,
                         String contactDetails,
                         FacilityCapacity courtCount,
                         boolean isActive) {
        return new Facility(id, userId, facilityName, phoneNumber, facilityDescription, location, city, district, contactDetails, courtCount, isActive);
    }
}
