package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import lombok.Getter;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Address;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.City;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import gtu.cse.se.altefdirt.aymoose.shared.domain.PhoneNumber;

@Getter
public class Facility extends BaseAggregateRoot {

    private AggregateId ownerId;
    private PhoneNumber phoneNumber;
    private String name;
    private String description;
    private Address address;
    private Location location;
    private String contactDetails;
    private boolean isActive;

    public Facility(AggregateId id,
            AggregateId ownerId,
            PhoneNumber phoneNumber,
            String name,
            String description,
            Address address,
            Location location,
            String contactDetails,
            boolean isActive) {
        super(id);
        this.ownerId = ownerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.address = address;
        this.location = location;
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
        this.name = name;
        this.description = description;
    }

    public AggregateId ownerId() {
        return this.ownerId;
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
}
