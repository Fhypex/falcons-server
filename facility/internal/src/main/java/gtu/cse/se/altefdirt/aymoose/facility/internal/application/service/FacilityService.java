package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;

public interface FacilityService {
    
    FacilityView denormalize(Facility facility);
}
