package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface CityRepository extends Repository<City, Long> {

    boolean existsByIdIn(List<Long> ids);
}
