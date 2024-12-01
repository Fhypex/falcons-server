package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import lombok.Getter;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Price;

@Getter
public class Court extends BaseAggregateRoot {

    public Court(AggregateId id,
            AggregateId userId,
            AggregateId facilityId,
            CourtDetails courtDetails,
            Measurements measurements,
            Capacity capacity,
            Price price) {
        super(id);
        this.userId = userId;
        this.facilityId = facilityId;
        this.details = courtDetails;
        this.measurements = measurements;
        this.capacity = capacity;
        this.price = price;
    }

    private AggregateId userId;

    private AggregateId facilityId;

    private CourtDetails details;

    private Measurements measurements;

    private Capacity capacity;

    private Price price;

    public Price price() {
        return price;
    }

    public AggregateId userId() {
        return userId;
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
}
