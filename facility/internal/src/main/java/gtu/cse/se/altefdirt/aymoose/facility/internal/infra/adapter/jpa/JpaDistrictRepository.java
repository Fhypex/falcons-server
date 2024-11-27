package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDistrictRepository extends JpaRepository<DistrictEntity, Long> {

    List<DistrictEntity> findAll();

    Optional<DistrictEntity> findById(Long id);

    @Query("SELECT COUNT(a) > 0 FROM DistrictEntity a WHERE a.id IN :ids")
    boolean existsByIdIn(List<Long> ids);

    @Query("SELECT COUNT(a) > 0 FROM DistrictEntity a WHERE a.cityId = :cityId AND a.name = :name")
    boolean existsByCityIdAndName(Long cityId, String name);

    @Query("SELECT a FROM DistrictEntity a WHERE a.cityId = :cityId")
    List<DistrictEntity> findAllByCityId(Long cityId);

    @Query("SELECT COUNT(a) = :size FROM DistrictEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<String> ids, int size);
}