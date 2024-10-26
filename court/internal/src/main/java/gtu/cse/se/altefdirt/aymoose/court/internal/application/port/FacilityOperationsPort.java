package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.UserData;

public interface FacilityOperationsPort {
    
    UserData findOwner(String facilityId);
}
