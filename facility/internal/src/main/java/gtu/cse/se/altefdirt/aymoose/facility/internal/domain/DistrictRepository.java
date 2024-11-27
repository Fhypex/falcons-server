package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.domain.Repository;

public interface DistrictRepository extends Repository<District, Long> {

    List<District> findByCityId(Long cityId);

    boolean existsByIdIn(List<Long> ids);

    boolean existsByCityIdAndName(Long cityId, String name);
}
