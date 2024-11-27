package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service;

import java.util.List;
import java.util.Set;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;

public interface CityService {
    
    CityView denormalize(City city);

    List<CityView> denormalizeInUseCitiesByDistricts(Set<Long> districts);
}
