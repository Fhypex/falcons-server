package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import lombok.Getter;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.City;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;

@Getter
public class Facility extends BaseAggregateRoot {

    private AggregateId userId;
    private PhoneNumber phoneNumber;
    private String facilityName;
    private String facilityDescription;
    private Location location;
    private City city;
    private String contactDetails;
    private boolean isActive;

    public Facility(AggregateId id,
            AggregateId userId,
            PhoneNumber phoneNumber,
            String facilityName,
            String facilityDescription,
            Location location,
            City city,
            String contactDetails,
            boolean isActive) {
        super(id);
        this.userId = userId;
        this.facilityName = facilityName;
        this.phoneNumber = phoneNumber;
        this.facilityDescription = facilityDescription;
        this.location = location;
        this.city = city;
        this.contactDetails = contactDetails;
        this.isActive = isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public void updateFacilityDetails(String name, String description) {
        this.facilityName = name;
        this.facilityDescription = description;
    }

}
