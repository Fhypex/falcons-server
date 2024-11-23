package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.config.CityConfig;
import gtu.cse.se.altefdirt.aymoose.shared.domain.City;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityConfig cityConfig;

    // In-memory map to store cities and their districts
    private Map<String, City> citiesMap = new HashMap<>();

    // Register cities and districts into the in-memory map
    public void registerCities() {
        for (CityConfig.CityConfigItem cityConfigItem : cityConfig.getCities()) {
            List<City.District> districts = cityConfigItem.getDistricts().stream()
                    .map(districtConfigItem -> new City.District(districtConfigItem.getName()))
                    .toList();
            City city = new City(cityConfigItem.getName(), districts);
            citiesMap.put(city.getName(), city);
        }
    }

    // Method to get a city by its name
    public City getCityByName(String name) {
        return citiesMap.get(name);
    }

    // Method to get all cities
    public Map<String, City> getAllCities() {
        return citiesMap;
    }
}