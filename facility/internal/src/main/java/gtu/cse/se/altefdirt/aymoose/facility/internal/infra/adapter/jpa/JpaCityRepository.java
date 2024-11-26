package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCityRepository extends JpaRepository<CityEntity, Long> {

    List<CityEntity> findAll();

    Optional<CityEntity> findById(Long id);

    @Query("SELECT COUNT(a) > 0 FROM CityEntity a WHERE a.id IN :ids")
    boolean existsByIdIn(List<Long> ids);

    @Query("SELECT COUNT(a) > 0 FROM CityEntity a WHERE a.id = :id")
    boolean existsById(Long id);
}