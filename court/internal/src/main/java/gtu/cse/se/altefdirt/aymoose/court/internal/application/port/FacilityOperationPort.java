package gtu.cse.se.altefdirt.aymoose.court.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.UserData;

public interface FacilityOperationPort {
    
    UserData findOwner(String facilityId);

    boolean isOwner(String facilityId, String userId);

    boolean existsById(String facilityId);
}
