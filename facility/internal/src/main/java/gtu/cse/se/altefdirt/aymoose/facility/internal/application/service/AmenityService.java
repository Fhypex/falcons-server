package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface AmenityService {
    
    AmenityView denormalize(Amenity amenity);

    boolean validateAmenities(List<AggregateId> amenities);
}
