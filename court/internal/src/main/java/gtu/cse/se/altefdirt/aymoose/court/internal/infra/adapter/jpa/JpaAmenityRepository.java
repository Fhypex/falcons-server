package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAmenityRepository extends JpaRepository<AmenityEntity, Long> {

    List<AmenityEntity> findAll();

    Optional<AmenityEntity> findById(Long id);

    List<AmenityEntity> findByAmenityId(String amenityId);
}