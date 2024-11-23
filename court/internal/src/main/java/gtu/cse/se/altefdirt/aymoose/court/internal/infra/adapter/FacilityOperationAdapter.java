package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.UserData;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.FacilityOperationPort;

@Component
class FacilityOperationAdapter implements FacilityOperationPort {
    
    @Override
    public UserData findOwner(String facilityId) {
        return new UserData("some-mocked-user-id");
    }

    @Override
    public boolean isOwner(String facilityId, String userId) {
        return true;
    }
}
