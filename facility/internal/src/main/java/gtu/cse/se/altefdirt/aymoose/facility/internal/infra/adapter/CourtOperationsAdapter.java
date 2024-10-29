package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CourtData;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.CourtOperationsPort;

@Component
class CourtOperationsAdapter implements CourtOperationsPort {
    
    @Override
    public CourtData findCourtDetails(String facilityId) {
       
        return new CourtData("mocked-court-details");
    }
}

