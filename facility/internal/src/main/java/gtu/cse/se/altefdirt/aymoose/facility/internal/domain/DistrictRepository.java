package gtu.cse.se.altefdirt.aymoose.facility.internal.domain;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository {

    District save(District city);

    Optional<District> findById(Long id);

    List<District> findAllByCityId(Long cityId);

    List<District> findAll();

    List<District> findAll(List<Long> ids);

    boolean existsByIdIn(List<Long> ids);

    boolean exists(Long id);

    boolean existsByCityIdAndName(Long cityId, String name);

    List<District> findAllByInUseTrue();
}
