package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FavoritesView;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.Favorites;

public interface FavoritesService {

    FavoritesView denormalize(Favorites favorites);
}
