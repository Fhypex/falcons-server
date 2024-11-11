package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.service.impl;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FavoritesView;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.service.FavoritesService;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.Favorites;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FavoritesServiceImpl implements FavoritesService {

    @Override
    public FavoritesView denormalize(Favorites favorites) 
    {
        return new FavoritesView(
            favorites.id().value(),
            favorites.userId().value(),
            favorites.facilityId().value()
        );
    }
}
