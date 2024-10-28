package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import lombok.Getter;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;

@Getter
public class Facility extends BaseAggregateRoot {

    private AggregateId userId;
    private String facilityName;
    private String phoneNumber;
    private String facilityDescription;
    private Location location;
    private String contactDetails;
    private FacilityCapacity courtCount;
    private boolean isActive;

    public Facility(AggregateId id, 
                    AggregateId userId, 
                    String facilityName, 
                    String phoneNumber,
                    String facilityDescription, 
                    Location location, 
                    String contactDetails,
                    FacilityCapacity courtCount,
                    boolean isActive) {
        super(id);
        this.userId = userId;
        this.facilityName = facilityName;
        this.phoneNumber = phoneNumber;
        this.facilityDescription = facilityDescription;
        this.location = location;
        this.contactDetails = contactDetails;
        this.courtCount = courtCount;
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

    public void updateContactDetails(String contactDetails, String phoneNumber) {
        this.contactDetails = contactDetails;
        this.phoneNumber = phoneNumber;
    }

    public void updateLocation(double latitude, double longitude) {
        this.location = new Location(latitude, longitude);
    }

    public void updateCourtCount(int count) {
        this.courtCount = new FacilityCapacity(count);
    }

    public AggregateId userId() {
        return userId;
    }

    public String facilityName() {
        return facilityName;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String facilityDescription() {
        return facilityDescription;
    }

    public Location location() {
        return location;
    }

    public String contactDetails() {
        return contactDetails;
    }

    public FacilityCapacity courtCount() {
        return courtCount;
    }

    public boolean isActive() {
        return isActive;
    }
}
