package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import lombok.Getter;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;

@Getter
public class Facility extends BaseAggregateRoot {

    private AggregateId userId;
    private String facilityName;
    private String phoneNumber;
    private String facilityDescription;
    private String location;
    private String city;
    private String district;
    private String contactDetails;
    private FacilityCapacity courtCount;
    private boolean isActive;

    public Facility(AggregateId id, 
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
        super(id);
        this.userId = userId;
        this.facilityName = facilityName;
        this.phoneNumber = phoneNumber;
        this.facilityDescription = facilityDescription;
        this.location = location;
        this.city = city;
        this.district = district;
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

    public void updateLocation(String location, String city, String district) {
        this.location = location;
        this.city = city;
        this.district = district;
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

    public String location() {
        return location;
    }

    public String city() {
        return city;
    }

    public String district() {
        return district;
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
