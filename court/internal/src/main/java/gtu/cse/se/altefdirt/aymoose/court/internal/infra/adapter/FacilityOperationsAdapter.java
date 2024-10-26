package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.UserData;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.FacilityOperationsPort;

@Component
class FacilityOperationsAdapter implements FacilityOperationsPort {
    
    @Override
    public UserData findOwner(String facilityId) {
        return new UserData("some-mocked-user-id");
    }
}
