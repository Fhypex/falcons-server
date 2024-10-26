package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;

@Getter
public class Court extends BaseAggregateRoot {
   
    public Court(AggregateId id, 
                 AggregateId facilityId, 
                 CourtDetails courtDetails,  
                 Measurements measurements, 
                 Capacity capacity,
                 WorkHours workHours,
                 Location location,
                 List<Amenity> amenities,
                 boolean isActive) {
        super(id);
        this.facilityId = facilityId;
        this.details = courtDetails;
        this.measurements = measurements;
        this.capacity = capacity;
        this.workHours = workHours;
        this.location = location;
        this.amenities = amenities;
        this.isActive = isActive;
    }

    private AggregateId facilityId;

    private CourtDetails details;

    private Measurements measurements;

    private Capacity capacity; 

    private WorkHours workHours;

    private Location location;

    private List<Amenity> amenities;

    private boolean isActive;

    public void disable() {
        this.isActive = false;
    }

    public void enable() {
        this.isActive = true;
    }

    public void updateDetails(String name, String description) {
        this.details = new CourtDetails(name, description);
    }

    public void updateMeasurements(int height, int width) {
        this.measurements = new Measurements(height, width);
    }

    public void updateCapacity(int value) {
        this.capacity = new Capacity(value);
    }

    public void updateWorkHours(Instant openTime, Instant closeTime) {
        this.workHours = new WorkHours(openTime, closeTime);
    }

    public void updateLocation(double latitude, double longitude) {
        this.location = new Location(latitude, longitude);
    }

    public void updateAmenities(List<String> amenityIds) {
        this.amenities = amenityIds.stream().map(AggregateId::from).map(Amenity::new).toList();
    }

    public AggregateId facilityId() {
        return facilityId;
    }

    public CourtDetails details() {
        return details;
    }

    public Measurements measurements() {
        return measurements;
    }

    public Capacity capacity() {
        return capacity;
    }

    public WorkHours workHours() {
        return workHours;
    }

    public Location location() {
        return location;
    }

    public List<Amenity> amenities() {
        return amenities;
    }

    public boolean isActive() {
        return isActive;
    }
}
