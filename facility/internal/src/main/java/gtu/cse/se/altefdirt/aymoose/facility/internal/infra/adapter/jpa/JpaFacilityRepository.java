package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFacilityRepository extends JpaRepository<FacilityEntity, UUID> {

    List<FacilityEntity> findAll();

    Optional<FacilityEntity> findById(UUID id);

    @Query("SELECT COUNT(a) = :size FROM FacilityEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<UUID> ids, int size);

    @Query("SELECT COUNT(a) > 0 FROM FacilityEntity a WHERE a.districtId = :districtId")
    boolean existsByDistrictId(Long districtId);

    @Query("SELECT COUNT(a) > 0 FROM FacilityEntity a WHERE a.districtId IN :districtIds")
    boolean existsByDistrictIds(List<Long> districtIds);

    @Query("SELECT COUNT(a) > 0 FROM FacilityEntity a WHERE a.id = :id AND a.userId = :userId")
    boolean existsByIdAndOwnerId(UUID id, UUID userId);

    @Query("DELETE FROM FacilityEntity a WHERE a.userId = :userId")
    int deleteByOwnerId(UUID userId);
}
