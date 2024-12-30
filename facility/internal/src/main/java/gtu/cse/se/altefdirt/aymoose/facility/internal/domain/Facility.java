package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import lombok.Getter;
import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Address;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;
import gtu.cse.se.altefdirt.aymoose.shared.domain.WorkHours;

@Getter
public class Facility extends BaseAggregateRoot {

    private AggregateId userId;
    private PhoneNumber phoneNumber;
    private String name;
    private String description;
    private Address address;
    private Location location;
    private String contactDetails;
    private WorkHours workHours;
    private List<AggregateId> amenities;
    private boolean isActive;

    public Facility(AggregateId id,
            AggregateId userId,
            PhoneNumber phoneNumber,
            String name,
            String description,
            Address address,
            Location location,
            String contactDetails,
            WorkHours workHours,
            List<AggregateId> amenities,
            boolean isActive) {
        super(id);
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.address = address;
        this.location = location;
        this.contactDetails = contactDetails;
        this.workHours = workHours;
        this.amenities = amenities;
        this.isActive = isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }

    public void updateFacilityDetails(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public AggregateId userId() {
        return this.userId;
    }

    public PhoneNumber phoneNumber() {
        return this.phoneNumber;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public Location location() {
        return this.location;
    }

    public String contactDetails() {
        return this.contactDetails;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public Address address() {
        return this.address;
    }

    public WorkHours workHours() {
        return this.workHours;
    }

    public List<AggregateId> amenities() {
        return this.amenities;
    }

    public void updateAmenities(List<AggregateId> amenities) {
        this.amenities = amenities;
    }

    public void updateLocation(String location) {
        this.location = new Location(location);
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateFullAddress(String fullAddress) {
        this.address = new Address(this.address.cityId(), this.address.districtId(), fullAddress);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void updateContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void updateOpenTime(int openTime) {
        this.workHours = new WorkHours(openTime, this.workHours.closeTime());
    }

    public void updateCloseTime(int closeTime) {
        this.workHours = new WorkHours(this.workHours.openTime(), closeTime);
    }

    public void setAmenities(List<AggregateId> amenities) {
        this.amenities = amenities;
    }

    public void updateIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void updateDistrictId(Long districtId) {
        this.address = new Address(this.address.cityId(), districtId, this.address.fullAddress());
    }

    @Override
    public String toString() {
        return "Facility{" +
                "userId=" + userId +
                ", phoneNumber=" + phoneNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", location=" + location +
                ", contactDetails='" + contactDetails + '\'' +
                ", workHours=" + workHours +
                ", amenities=" + amenities +
                ", isActive=" + isActive +
                '}';
    }
}
