package gtu.cse.se.altefdirt.aymoose.favorites.internal.infra.adapter.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.Favorites;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.FavoritesFactory;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.FavoritesRepository;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.infra.adapter.jpa.FavoritesEntity;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.infra.adapter.jpa.JpaFavoritesRepository;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
class FavoritesRepositoryImpl implements FavoritesRepository {

    private final JpaFavoritesRepository jpaFavoritesRepository;
    private final FavoritesFactory favoritesFactory;

    private Favorites build(FavoritesEntity favoritesEntity) {
        return favoritesFactory.load(
                AggregateId.fromUUID(favoritesEntity.getId()), 
                AggregateId.fromUUID(favoritesEntity.getUserId()), 
                AggregateId.fromUUID(favoritesEntity.getFacilityId())
        );
    }

    @Override
    public Favorites save(Favorites favorites) {
        FavoritesEntity favoritesEntity = jpaFavoritesRepository.save(FavoritesEntity.from(
                favorites.id(),
                favorites.userId(),
                favorites.facilityId()
        ));
        return build(favoritesEntity);
    }

    @Override
    public Optional<Favorites> findById(AggregateId id) {
        return jpaFavoritesRepository.findById(id.value()).map(this::build);
    }

    @Override
    public List<Favorites> findAll() {
        return jpaFavoritesRepository.findAll().stream()
                                      .map(this::build)
                                      .collect(Collectors.toList());
    }

    public List<Favorites> findByUserId(AggregateId userId) {
        return jpaFavoritesRepository.findByUserId(userId.value()).stream()
                                      .map(this::build)
                                      .collect(Collectors.toList());
    }

    public void deleteByUserIdAndFacilityId(AggregateId userId, AggregateId facilityId) {
        jpaFavoritesRepository.deleteByUserIdAndFacilityId(userId.value(), facilityId.value());
    }
}
