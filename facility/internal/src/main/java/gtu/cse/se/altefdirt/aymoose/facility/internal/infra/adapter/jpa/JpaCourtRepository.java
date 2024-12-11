package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourtRepository extends JpaRepository<CourtEntity, UUID> {

    List<CourtEntity> findAll();

    Optional<CourtEntity> findById(UUID id);

    @Query("SELECT c FROM CourtEntity c WHERE c.facilityId = :facilityId")
    List<CourtEntity> findAllByFacilityId(UUID facilityId);

    @Query("DELETE FROM CourtEntity c WHERE c.facilityId = :facilityId")
    int deleteByFacilityId(UUID facilityId);

    @Query("SELECT COUNT(a) = :size FROM CourtEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<UUID> ids, int size);

    @Query("SELECT COUNT(a) = 1 FROM CourtEntity a WHERE a.id = :id AND a.userId = :userId")
    boolean existsByIdAndOwnerId(UUID id, UUID userId);

}