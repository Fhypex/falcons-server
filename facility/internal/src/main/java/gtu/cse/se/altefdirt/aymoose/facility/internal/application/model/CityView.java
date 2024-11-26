package gtu.cse.se.altefdirt.aymoose.facility.internal.application.model;

import java.util.List;
import java.util.Map;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import lombok.Builder;

@Builder
public record CityView(
        Long id,
        String name,
        List<Map<String, Object>> districts) {
    public CityView(City city, List<District> districts) {
        this(city.id(),
        city.name(),
        districts.stream().map(District::toMap).toList());
    }
}
