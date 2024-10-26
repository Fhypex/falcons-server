package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;

@Component
public class CourtFactory {
    
    public Court create(AggregateId facilityId, 
                        CourtDetails courtDetails,  
                        Measurements measurements, 
                        Capacity capacity,
                        WorkHours workHours,
                        Location location,
                        List<Amenity> amenities) {
        return new Court(AggregateId.generate(), facilityId, courtDetails, measurements, capacity, workHours, location, amenities, true);
    }


    public Court load(AggregateId id, 
                      AggregateId facilityId, 
                      CourtDetails courtDetails,  
                      Measurements measurements, 
                      Capacity capacity,
                      WorkHours workHours,
                      Location location,
                      List<Amenity> amenities,
                      boolean isActive) {
        return new Court(id, facilityId, courtDetails, measurements, capacity, workHours, location, amenities, isActive);
    }
}
