package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFacilityRepository extends JpaRepository<FacilityEntity, String> {

    List<FacilityEntity> findAll();

    Optional<FacilityEntity> findById(String id);

    @Query("SELECT COUNT(a) = :size FROM FacilityEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<String> ids, int size);

    @Query("SELECT COUNT(a) > 0 FROM FacilityEntity a WHERE a.districtId = :districtId")
    boolean existsByDistrictId(Long districtId);

    @Query("SELECT COUNT(a) > 0 FROM FacilityEntity a WHERE a.districtId IN :districtIds")
    boolean existsByDistrictIds(List<Long> districtIds);
}
