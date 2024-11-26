package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAmenityRepository extends JpaRepository<AmenityEntity, String> {

    List<AmenityEntity> findAll();

    Optional<AmenityEntity> findById(String id);

    @Query("SELECT COUNT(a) = :size FROM AmenityEntity a WHERE a.id IN :ids")
    boolean existsByIdIn(List<String> ids, int size);
}