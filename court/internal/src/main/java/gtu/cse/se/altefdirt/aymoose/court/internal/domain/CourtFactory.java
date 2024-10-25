package gtu.cse.se.altefdirt.aymoose.court.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;

public class CourtFactory {
    
    public Court create(AggregateId facilityId, 
                        CourtDetails courtDetails,  
                        Measurements measurements, 
                        Capacity capacity,
                        WorkHours workHours,
                        Location location) {
        return new Court(AggregateId.generate(), facilityId, courtDetails, measurements, capacity, workHours, location);
    }


    public Court load(AggregateId id, 
                      AggregateId facilityId, 
                      CourtDetails courtDetails,  
                      Measurements measurements, 
                      Capacity capacity,
                      WorkHours workHours,
                      Location location) {
        return new Court(id, facilityId, courtDetails, measurements, capacity, workHours, location);
    }
}
