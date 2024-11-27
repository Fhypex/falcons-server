package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, String> {

    List<ImageEntity> findAll();

    Optional<ImageEntity> findById(String id);

    List<ImageEntity> findAllByRelationId(String relationId);

    Integer deleteByRelationId(String relationId);
}