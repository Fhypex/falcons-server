package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FacilityData;

public interface FacilityOperationsPort {
    
    FacilityData findFacility(String facilityId);
    
    List<FacilityData> findFacilitiesByUserId(String userId);
}
