package gtu.cse.se.altefdirt.aymoose.favorites.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import org.springframework.stereotype.Component;

@Component
public class FavoritesFactory {

    public Favorites create(AggregateId userId, AggregateId facilityId) {
        return new Favorites(AggregateId.generate(), userId, facilityId);
    }

    public Favorites load(AggregateId id, AggregateId userId, AggregateId facilityId) {
        return new Favorites(id, userId, facilityId);
    }
}
