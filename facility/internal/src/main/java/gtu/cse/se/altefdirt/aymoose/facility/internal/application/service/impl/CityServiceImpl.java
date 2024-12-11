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

        List<District> districts = districtRepository.findByCityId(city.id());
        return new CityView(city, districts);
    }

    @Override
    public List<CityView> denormalizeInUseCitiesByDistricts(Set<Long> districts) {
        List<District> allDistricts = districtRepository.findByIds(districts.stream().toList()).stream().toList();
        Set<Long> cityIds = allDistricts.stream().map(District::cityId).collect(Collectors.toUnmodifiableSet());
        List<City> cities = cityRepository.findByIds(cityIds.stream().toList());
        return cities.stream().map(city -> {
            List<District> cityDistricts = allDistricts.stream().filter(district -> district.cityId().equals(city.id()))
                    .toList();
            return new CityView(city, cityDistricts);
        }).toList();
    }
}
