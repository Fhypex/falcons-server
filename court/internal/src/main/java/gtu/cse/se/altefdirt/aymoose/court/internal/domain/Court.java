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
                 Location location) {
        super(id);
        this.facilityId = facilityId;
        this.details = courtDetails;
        this.measurements = measurements;
        this.capacity = capacity;
        this.workHours = workHours;
        this.location = location;
    }

    private AggregateId facilityId;

    private CourtDetails details;

    private Measurements measurements;

    private Capacity capacity; 

    private WorkHours workHours;

    private Location location;

    private List<String> images;

    public AggregateId facilityId() {
        return facilityId;
    }

    public String name() {
        return details.name();
    }

    public String description() {
        return details.description();
    }

    public int height() {
        return measurements.height();
    }

    public int width() {
        return measurements.width();
    }

    public int capacity() {
        return capacity.capacity();
    }

    public Instant openTime() {
        return workHours.openTime();
    }

    public Instant closeTime() {
        return workHours.closeTime();
    }

    public Double latitude() {
        return location.latitude();
    }

    public Double longitude() {
        return location.longitude();
    }
}
