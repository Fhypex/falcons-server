package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;
import java.util.Optional;

public interface CityRepository {

    City save(City city);

    Optional<City> findById(Long id);

    List<City> findAll();

    List<City> findAll(List<Long> ids);

    boolean existsByIdIn(List<Long> ids);

    boolean existsById(Long id);

}
