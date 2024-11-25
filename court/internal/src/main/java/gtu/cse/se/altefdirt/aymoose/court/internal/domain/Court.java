package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import lombok.Getter;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;

@Getter
public class Court extends BaseAggregateRoot {

    public Court(AggregateId id,
            AggregateId facilityId,
            CourtDetails courtDetails,
            Measurements measurements,
            Capacity capacity,
            boolean isActive) {
        super(id);
        this.facilityId = facilityId;
        this.details = courtDetails;
        this.measurements = measurements;
        this.capacity = capacity;
        this.isActive = isActive;
    }

    private AggregateId facilityId;

    private CourtDetails details;

    private Measurements measurements;

    private Capacity capacity;

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

    public boolean isActive() {
        return isActive;
    }
}
