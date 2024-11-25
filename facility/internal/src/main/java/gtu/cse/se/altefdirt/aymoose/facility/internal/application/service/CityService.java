package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;

public interface CityService {
    
    CityView denormalize(City city);

    List<CityView> denormalizeInUseCitiesByDistricts(List<District> districts);
}
