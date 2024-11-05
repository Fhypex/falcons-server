package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFacilityRepository extends JpaRepository<FacilityEntity, String> {

    List<FacilityEntity> findAll();

    Optional<FacilityEntity> findById(String id);

    /* 
    @Query("SELECT f FROM FacilityEntity f WHERE " +
    "(:city IS NULL OR f.city = :city) AND " +
    "(:district IS NULL OR f.district = :district) AND " +
    "(:name IS NULL OR f.name LIKE %:name%)")
List<FacilityEntity> findByFilters(@Param("city") String city,
                                @Param("district") String district,
                                @Param("name") String name); */
}
