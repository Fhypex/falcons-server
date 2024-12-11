package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAmenityRepository extends JpaRepository<AmenityEntity, UUID> {

    List<AmenityEntity> findAll();

    Optional<AmenityEntity> findById(UUID id);

    @Query("SELECT COUNT(a) = :size FROM AmenityEntity a WHERE a.id IN :ids")
    boolean existsByIdIn(List<UUID> ids, int size);
}