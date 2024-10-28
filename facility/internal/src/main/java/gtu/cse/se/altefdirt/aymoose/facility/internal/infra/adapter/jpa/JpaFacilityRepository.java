package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFacilityRepository extends JpaRepository<FacilityEntity, String> {

    List<FacilityEntity> findAll();

    Optional<FacilityEntity> findById(String id);
}
