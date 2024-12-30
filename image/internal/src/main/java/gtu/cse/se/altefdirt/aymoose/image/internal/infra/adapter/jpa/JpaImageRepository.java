package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, UUID> {

    List<ImageEntity> findAll();

    Optional<ImageEntity> findById(UUID id);

    List<ImageEntity> findAllByRelationId(UUID relationId);

    @Query("SELECT i FROM ImageEntity i WHERE i.relationId IN :relationIds")
    List<ImageEntity> findAllByRelationIds(List<UUID> relationIds);

    Integer deleteByRelationId(UUID relationId);
}