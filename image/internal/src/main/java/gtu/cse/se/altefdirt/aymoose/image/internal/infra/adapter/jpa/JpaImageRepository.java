package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, UUID> {

    List<ImageEntity> findAll();

    Optional<ImageEntity> findById(UUID id);

    List<ImageEntity> findAllByRelationId(UUID relationId);

    Integer deleteByRelationId(UUID relationId);
}