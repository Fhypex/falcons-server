package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourtRepository extends JpaRepository<CourtEntity, String> {

    List<CourtEntity> findAll();

    Optional<CourtEntity> findById(String id);

    @Query("SELECT c FROM CourtEntity c WHERE c.facilityId = :facilityId")
    List<CourtEntity> findAllByFacilityId(String facilityId);

    @Query("DELETE FROM CourtEntity c WHERE c.facilityId = :facilityId")
    int deleteByFacilityId(String facilityId);

    @Query("SELECT COUNT(a) = :size FROM CourtEntity a WHERE a.id IN :ids")
    boolean existsByIds(List<String> ids, int size);

}