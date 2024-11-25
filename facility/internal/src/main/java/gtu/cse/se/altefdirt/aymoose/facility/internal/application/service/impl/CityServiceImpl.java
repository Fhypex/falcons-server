package gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.impl;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.CityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CityServiceImpl implements CityService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    @Override
    public CityView denormalize(City city) {

        List<District> districts = districtRepository.findAllByCityId(city.id());
        return new CityView(city, districts);
    }

    @Override
    public List<CityView> denormalizeInUseCitiesByDistricts(List<District> districts) {
        Set<Long> cityIds = districts.stream().map(District::cityId).collect(Collectors.toUnmodifiableSet());
        List<City> cities = cityRepository.findAll(cityIds.stream().collect(Collectors.toUnmodifiableList()));
        return cities.stream().map(city -> {
            List<District> cityDistricts = districts.stream().filter(district -> district.cityId().equals(city.id()))
                    .collect(Collectors.toList());
            return new CityView(city, cityDistricts);
        }).collect(Collectors.toUnmodifiableList());

    }
}
