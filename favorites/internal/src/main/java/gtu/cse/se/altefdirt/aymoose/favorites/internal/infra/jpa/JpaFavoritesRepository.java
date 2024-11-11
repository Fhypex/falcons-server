package gtu.cse.se.altefdirt.aymoose.favorites.internal.infra.adapter.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFavoritesRepository extends JpaRepository<FavoritesEntity, String> {

    List<FavoritesEntity> findAll();

    Optional<FavoritesEntity> findById(String id);

    List<FavoritesEntity> findByUserId(String userId);

    void deleteByUserIdAndFacilityId(String userId, String facilityId);
}
