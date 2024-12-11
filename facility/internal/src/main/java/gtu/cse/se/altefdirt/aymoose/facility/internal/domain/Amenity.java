package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;

public class Amenity extends BaseAggregateRoot {

    private String name;

    public Amenity(AggregateId id, String name) {
        super(id);
        this.name = name;
    }

    public String name() {
        return name;
    }
}
