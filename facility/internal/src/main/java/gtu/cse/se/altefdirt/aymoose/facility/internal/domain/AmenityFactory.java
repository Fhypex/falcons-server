package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;


@Component
public class AmenityFactory {

    public Amenity create(String name) {
        return new Amenity(AggregateId.generate(), name);
    }

    public Amenity load(AggregateId id,
            String name) {
        return new Amenity(id, name);
    }
}
