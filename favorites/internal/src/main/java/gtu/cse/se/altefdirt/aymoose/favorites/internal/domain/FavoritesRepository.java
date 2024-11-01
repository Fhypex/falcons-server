package gtu.cse.se.altefdirt.aymoose.favorites.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import java.util.List;
import java.util.Optional;

public interface FavoritesRepository {

    Favorites save(Favorites favorites);

    Optional<Favorites> findByUserIdAndFacilityId(AggregateId userId, AggregateId facilityId);

    List<Favorites> findByUserId(AggregateId userId);

    void delete(Favorites favorites);
}
