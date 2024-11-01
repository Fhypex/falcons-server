package gtu.cse.se.altefdirt.aymoose.favorites.internal.infra.adapter;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FacilityData;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.port.FacilityOperationsPort;

@Component
class FacilityOperationsAdapter implements FacilityOperationsPort {
    
    @Override
    public FacilityData findFacilityDetails(String facilityId) {
    
        return new FacilityData("mocked-facility-id", "mocked-facility-name");
    }
}

